package com.example.akkar2.services;

import com.example.akkar2.entities.*;
import com.example.akkar2.repository.ClientRepository;
import com.example.akkar2.repository.MembershipRepository;
import com.example.akkar2.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class ClientService implements IClientService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    MembershipRepository membershipRepository;
    @Autowired
    ClientRepository ClientRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public ResponseEntity<?> addClient(Client c) {
        if (c.getFirstname().isEmpty()  || c.getFirstname().length()<3){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter your first name. ");
        }
        else if(c.getLastname().isEmpty()  || c.getLastname().length()<3){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter your Last name. ");
        }

        else if ( c.getEmail() == null || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", c.getEmail()) ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter your E-mail address. ");
        } else if (c.getPassword().length()<8) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Password min 8 length. ");
        }
        c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
        ClientRepository.save(c);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(c.getEmail());
        mailMessage.setSubject("NEW MEMBERSHIP !");
        mailMessage.setFrom("akkarpidev@gmail.com");
        mailMessage.setText("WELCOME ! "+c.getFirstname()+" "+c.getLastname()+"\n YOU ARE NOW A NEW MEMBER IN AKKAR \n ");

        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @Override
    public List<Client> retrieveAllClient() {
        return (List<Client>) ClientRepository.findAll();
    }
    @Override
    public Client retrieveClient(int id) {

        return  ClientRepository.findById(id).orElse(new Client());


    }
    @Override
    public ResponseEntity<?> removeClient(int id) {
        Optional<Client> client = ClientRepository.findById(id);
        if(client.isPresent()){
            ClientRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Client deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("there is no such Client with this id: "+id);
    }

    @Override
    public Client updateClient(Client c) {
        return ClientRepository.save(c);
    }
    @Override
    public Client login(String username, String password) {
        Client user = (Client) userRepository.findUserByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (user.getBanned()) {
            throw new RuntimeException("User is banned");
        }

        if (password.equals(user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return user;
    }

    @Override
    public CustomerData createCustomer(CustomerData data) throws StripeException {
        Stripe.apiKey = "sk_test_51MeOYDABpRc42fS3JqUc8NArklioPhv7OJqiqfd7aQ0Db2DaSS7O0t13bc1aBvuWlPHjZXotd3qXSDMTT3hdtaFr00aHyzBalw";
        Map<String, Object> params = new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());
        Customer customer = Customer.create(params);
        data.setCustomerId(customer.getId());
        return data;
    }

    @Override
    public ResponseEntity<String> chargeCard(int clientid, Membership_prices amount, String number, int exp_month , int exp_year, int cvc) {
        Membership membership = new Membership();
        membership.setIdclient(clientid);
        membership.setAmount(amount.getValue());
        membershipRepository.save(membership);
        if (amount.getValue() == 500)
            ClientRepository.updateAnnouncementAvailibility(clientid,5);
        else
            ClientRepository.updateAnnouncementAvailibility(clientid,10);
        try {

            try {
                Map<String, Object> params = new HashMap<>();
                Map<String, Object> tokenParams = new HashMap<>();
                Map<String, Object> cardParams = new HashMap<>();

                cardParams.put("number", number);
                cardParams.put("exp_month", exp_month);
                cardParams.put("exp_year", exp_year);
                cardParams.put("cvc", cvc);

                tokenParams.put("card", cardParams);
                Token token = Token.create(tokenParams);

                if (token.getId()!=null){
                    params.put("amount",amount.getValue());
                    params.put("description", "payement ");
                    params.put("currency", "eur");
                    params.put("source", token.getId());
                    Charge.create(params);


                }


                return ResponseEntity.ok("Payment successful");

            } catch (StripeException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
        }

    }
    @Override
    public String passwordreset (@PathVariable("login") String login) {
        String msg="";
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();



        Client client = ( Client) userRepository.findUserByEmail(login);
        client.setPassword(generatedString) ;
        updateClient(client);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(client.getEmail());
        mailMessage.setSubject("PASSWORD RESET !");
        mailMessage.setFrom(" akkarpidev@gmail.com");

        mailMessage.setText("Dear"+client.getFirstname()+" YOUR NEW PASSWORD IS : "+generatedString);

        emailSenderService.sendEmail(mailMessage);

        return msg; }


}

