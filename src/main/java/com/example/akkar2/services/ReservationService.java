package com.example.akkar2.services;

import com.example.akkar2.entities.Reservation;
import com.example.akkar2.repository.RealEstateRepository;
import com.example.akkar2.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
@Service
public class ReservationService implements  IReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    RealEstateRepository realEstateRepo;


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
            // TODO: Implement cost calculation based on room type, season, etc.
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
        return reservationRepository.save(r);
    }
*/

