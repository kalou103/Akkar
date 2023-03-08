package com.example.akkar2.controllers;

import com.example.akkar2.QrCode.CodeGenerator;
import com.example.akkar2.email.EmailSenderService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;

@RequestMapping("/barcodes")
@RestController
public class QRCodeGenController {

   /* @PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception{
        return successResponse(CodeGenerator.generateQRCode(barcode));//generate Qr code and returend as an image
    }

    private ResponseEntity<BufferedImage> successResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();// to convert a BufferedImage object into an HTTP response with the appropriate content type (image/png in this case),
    }*/
   private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/img/QRCode.png";
@Autowired
JavaMailSender mailSender;
    @GetMapping("/")
    public String getQRCode(Model model) throws MessagingException {
        String medium="ilyes";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = CodeGenerator.getQRCodeImage(medium,250,250);

            // Generate and Save Qr Code Image in static/image folder
            CodeGenerator.generateQRCodeImage(medium,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        //

        // Send the email with the QR Code image
        String recipientEmail = "ilyesnakhlii188@gmail.com";
        String subject = "QR Code";

// Create a new MimeMessage and set its properties
        MimeMessage message = mailSender.createMimeMessage();//allows for the inclusion of text, HTML, images, and attachments in a single message.
        MimeMessageHelper helper = new MimeMessageHelper(message, true);//It provides methods for adding various types of content, such as plain text, HTML, and attachments, to an email message
        helper.setFrom("ilyes.nakhli@esprit.tn");
        helper.setTo(recipientEmail);
        helper.setSubject(subject);

// Create a new ByteArrayDataSource with the Base64 encoded string
        ByteArrayDataSource dataSource = new ByteArrayDataSource(Base64.getDecoder().decode(qrcode), "image/png");

// Add the QR Code image as an attachment to the email
        helper.addAttachment("qrcode.png", dataSource);

// Set the body of the email
        helper.setText("ezezez");

// Send the email
        mailSender.send(message);

        System.out.println("Mail sent");
        //

        model.addAttribute("medium",medium);
        //model.addAttribute("github",github);
        model.addAttribute("qrcode",qrcode);

        return "qrcode";
    }

}
