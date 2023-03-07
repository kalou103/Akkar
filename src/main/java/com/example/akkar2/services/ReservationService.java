package com.example.akkar2.services;

import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.RealEstate;
import com.example.akkar2.entities.Reservation;
import com.example.akkar2.repository.ClientRepository;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.repository.ReservationRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.example.akkar2.entities.Sexe.Female;
import static com.example.akkar2.entities.Sexe.Male;

@Service
public class ReservationService implements  IReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RealEstateRepository realEstateRepo;
    @Autowired
    ClientRepository clientRepository;
    public List<Reservation> getAllReservationsByRealEstate(Long guestHouseId) {
        RealEstate Re =  realEstateRepo.findRealEstateByIdRealEstate(guestHouseId);
        return reservationRepository.findReservationByRealEstate(Re);
    }
    public Reservation createReservation(Reservation reservation, Long guestHouseId, int clientId) {
        //recherche du real estate et client
        RealEstate guestHouse = realEstateRepo.findRealEstateByIdRealEstate(guestHouseId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID"));

        // settina l real estate w client
        reservation.setRealEstate(guestHouse);
        reservation.setClient(client);

        //verification disponibilités

        boolean isAvailable = checkAvailability(guestHouse, reservation.getCheckInDate(), reservation.getCheckOutDate());

        if (!isAvailable) {
            throw new IllegalArgumentException("The guest house is not available for the specified date range.");
        }

        reservation.setPrepaymentstatus(false);
        reservation.setStatus(true);

        // calculs
        Double totalAmount = calculateTotalAmount(guestHouse, reservation.getCheckInDate(), reservation.getCheckOutDate());
        reservation.setTotalamount(totalAmount);
        Double prepaymentAmount = totalAmount * 0.3;
        reservation.setPrepaymentamount(prepaymentAmount);

        // nsaviw
        return reservationRepository.save(reservation);
    }
    public boolean checkAvailability(RealEstate guestHouse, LocalDate checkInDate, LocalDate checkOutDate) {

        List<Reservation> reservations = guestHouse.getReservations();

        for (Reservation reservation : reservations) {
            LocalDate reservationCheckInDate = reservation.getCheckInDate();
            LocalDate reservationCheckOutDate = reservation.getCheckOutDate();

            if (checkInDate.isEqual(reservationCheckInDate) || checkOutDate.isEqual(reservationCheckOutDate)) {
                return false;
            } else if (checkInDate.isAfter(reservationCheckInDate) && checkInDate.isBefore(reservationCheckOutDate)) {
                return false;
            } else if (checkOutDate.isAfter(reservationCheckInDate) && checkOutDate.isBefore(reservationCheckOutDate)) {
                return false;
            }
        }

        return true;
    }


    private Double calculateTotalAmount(RealEstate guestHouse, LocalDate checkInDate, LocalDate checkOutDate) {
        double days = ChronoUnit.DAYS.between( checkInDate, checkOutDate);
        double price =  guestHouse.getPricePerNight();
        return (double) (days * price) ;
    }
    public byte[] generatePdf(Long reservationId) throws IOException, DocumentException {
        Reservation reservation = reservationRepository.findReservationByIdRes(reservationId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Add reservation details to PDF
        Paragraph title = new Paragraph("Guest house reservation");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("This reservation is only available when the prepayment amount mentioned is paid, else " +
                "the guest house owner has the right to not accept the residents. " +
                "PS: The prepayment amount which is 30% of the total amount, is the only way to guarantee the reservation and make sure the owner" +
                "doesn't rent the house for other guests."));
        document.add(new Paragraph("ID: " + reservation.getIdRes()));
        document.add(new Paragraph("Check-in date: " + reservation.getCheckInDate()));
        document.add(new Paragraph("Check-out date: " + reservation.getCheckOutDate()));
       if (reservation.getClient().getSexe() == Male) {
            document.add(new Paragraph("Client name : Mr." + reservation.getClient().getLastname() + reservation.getClient().getFirstname()));
       } else if (reservation.getClient().getSexe() == Female) {
           document.add(new Paragraph("Client name : Mme." + reservation.getClient().getLastname() + reservation.getClient().getFirstname()));
       } else {
           document.add(new Paragraph("Client name : " + reservation.getClient().getLastname() + reservation.getClient().getFirstname()));
       }
           document.add(new Paragraph("Price per night : " + reservation.getRealEstate().getPricePerNight()+"00 DT"));
        document.add(new Paragraph("Amount to pay " + reservation.getTotalamount()+"00DT"));
        document.add(new Paragraph("Prepayment amount: " + reservation.getPrepaymentamount()+"00DT"));
        document.add(new Paragraph("Prepayment status: " + reservation.isPrepaymentstatus()));
        document.add(new Paragraph("Guest house location: " + reservation.getRealEstate().getLocation()));



        document.close();

        return outputStream.toByteArray();
    }
    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

   /* public void payPrepayment(int reservationId, String token) throws StripeException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        Charge charge = stripeService.chargeCreditCard(token, reservation.getPrepaymentAmount(), "Prepayment for guest house reservation");

        reservation.setPrepaymentStatus(charge.getStatus().equals("succeeded"));
        reservationRepository.save(reservation);
    }*/

    // ...





/*

    public static final String RESERVATION_DATE_TAKEN = "dateTaken";
    public static final String WRONG_DATES = "wrongDates";
    public static final String WRONG_NUMBER_OF_PERSONS = "wrongNumberOfPersons";
    private static final double TAX_RATE = 1.6;


    public Reservation addReservation(Reservation res) {
        Reservation newReservation = res;

        LocalDate startDate = newReservation.getCheckInDate();
        LocalDate endDate = newReservation.getCheckOutDate();

        if (checkIfEndDateIsAfterStartDate(startDate, endDate ) ) {
newReservation.setCheckInDate(startDate);
newReservation.setCheckOutDate(endDate);
reservationRepository.save(newReservation);
        }
        return newReservation;
    }

    public boolean checkIfReservationDateIsFree(Reservation givenReservation) {

        List<Reservation> reservationList = reservationRepository.findReservationByRealEstate(givenReservation.getRealEstate());

        LocalDate newStartDate = givenReservation.getCheckInDate();
        LocalDate newEndDate = givenReservation.getCheckOutDate();

        for (Reservation reservation : reservationList) {
            LocalDate reservedStartDate = reservation.getCheckInDate();
            LocalDate reservedEndDate = reservation.getCheckOutDate();
            if (checkIfNewReservationOverlapsExisting(newStartDate, newEndDate, reservedStartDate, reservedEndDate)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfNewReservationOverlapsExisting(LocalDate startDateToCheck, LocalDate endDateToCheck, LocalDate startDate, LocalDate endDate) {
        boolean startDateOverlapsExisting = checkIfDateIsBetween(startDateToCheck, startDate, endDate) || startDateToCheck.isEqual(startDate);
        boolean endDateOverlapsExisting = checkIfDateIsBetween(endDateToCheck, startDate, endDate) || endDateToCheck.isEqual(endDate);

        boolean existingReservationInsideNew = checkIfDateIsBetween(startDate, startDateToCheck, endDateToCheck) &&
                checkIfDateIsBetween(endDate, startDateToCheck, endDateToCheck);

        return startDateOverlapsExisting || endDateOverlapsExisting || existingReservationInsideNew;
    }

    private boolean checkIfDateIsBetween(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        return dateToCheck.isAfter(startDate) && dateToCheck.isBefore(endDate);
    }

    private boolean checkIfEndDateIsAfterStartDate(LocalDate startDate, LocalDate endDate) {
        if (!endDate.isAfter(startDate)) {
            return false;
        }
        return endDate.isAfter(startDate);
    }



    public double calculateCost(Reservation reservation) {
        double days = ChronoUnit.DAYS.between((Temporal) reservation.getCheckInDate(), (Temporal) reservation.getCheckOutDate());
             double price =  reservation.getRealEstate().getPricePerNight();
        return (double) days * price * TAX_RATE;
    }
}
     /*   public Reservation addReservation(RealEstate room,Client guest,  Date startDate, Date endDate) {
            Double cost = calculateReservationCost(room, startDate, endDate);
            Double prepaymentAmount = calculatePrepaymentAmount(cost);

            Reservation reservation = new Reservation();
            reservation.setClient(guest);
            reservation.setRealEstate(room);
            reservation.setCheckInDate(startDate);
            reservation.setCheckOutDate(endDate);
            reservation.setPrepaymentamount(prepaymentAmount);
            reservation.setPrepaymentstatus(false);
            reservation.setTotalamount(cost);
            reservation.setNumberofresidents(0);

            return reservationRepository.save(reservation);
        }

        private double calculateReservationCost(RealEstate room, Date startDate, Date endDate) {
            double days = ChronoUnit.DAYS.between((Temporal) startDate, (Temporal) endDate);
            return room.getPricePerNight() * days;
        }
    private double calculatePrepaymentAmount(double cost) {
        return cost * 0.3;
    }
    public boolean isRealEstateAvailable(RealEstate Re, Date startDate, Date endDate) {
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(Re, startDate, endDate);
        return conflictingReservations.isEmpty();
    }
    @Override
    public List<Reservation> retrieveAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }
    @Override
    public Reservation retrieveReservation(int id) {

        return reservationRepository.findById(id).orElse(new Reservation());


    }
    @Override
    public void removeReservation(int id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);*/
    }


