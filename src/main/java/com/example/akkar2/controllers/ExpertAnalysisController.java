package com.example.akkar2.controllers;


import com.example.akkar2.ImageUpload.FileUploadUtil;
import com.example.akkar2.PDFExporter.ExpertAnalysisPDF;
import com.example.akkar2.QrCode.CodeGenerator;
import com.example.akkar2.email.EmailSenderService;
import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.ExpertAppointment;
import com.example.akkar2.services.ClientService;
import com.example.akkar2.services.ExpertAnalysisService;
import com.example.akkar2.services.ExpertService;
import com.example.akkar2.services.IExpertService;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ExpertAnalysis")
public class ExpertAnalysisController {
    @Autowired
    ExpertAnalysisService expertAnalysisService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ExpertService expertService;
    @Autowired
    JavaMailSender mailSender;

    @PostMapping("/expert-analysis")
    public ExpertAnalysis addExpertAnalysis(@RequestBody ExpertAnalysis expert) {
        return expertAnalysisService.AddExpertAnalysis(expert);
    }


    @GetMapping
    public List<ExpertAnalysis> getAllExpertAnalysis() {
        return expertAnalysisService.ShowAllExpertAnalysis();
    }
    @DeleteMapping("/{expertAnalysisId}")
    public void DeleteExpert(@PathVariable long expertId)
    {
        expertAnalysisService.DeleteExpertAnalysis(expertId);
    }
    @PutMapping("/update-Expert")
    @ResponseBody
    public ExpertAnalysis updateExpertAnalysis(@RequestBody ExpertAnalysis c) {
        return expertAnalysisService.updateExpertAnalysist(c);

    }

    @GetMapping("/users/export/pdf/{expertAnalysisId}/{recipientId}")
    public void exportToPDF(HttpServletResponse response, @PathVariable int expertAnalysisId, @PathVariable int recipientId) throws DocumentException, IOException,MessagingException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        ExpertAnalysis expertAnalysis = expertAnalysisService.showExpertAnalysisByid(expertAnalysisId);

        ExpertAnalysisPDF exporter = new ExpertAnalysisPDF(expertAnalysis);
        exporter.export(response);

       // Generate a download link to the PDF file

        String pdfDownloadLink = "http://localhost:8089/spring/ExpertAnalysis/users/export/pdf/" + expertAnalysisId+"/"+recipientId;

        // Generate QR code for the download link
        byte[] qrcode = null;
        try {
            qrcode = CodeGenerator.getQRCodeImage(pdfDownloadLink, 150, 150);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        // Convert QR code byte array into Base64 encoded string
        String qrcodeData = Base64.getEncoder().encodeToString(qrcode);

          // Send the email with the QR Code image
        Client client = clientService.retrieveClient(recipientId);
        String subject = "QR Code";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("ilyes.nakhli@esprit.tn");
        helper.setTo(client.getEmail());
        helper.setSubject(subject);

        // Create a new ByteArrayDataSource with the Base64 encoded QR code
        ByteArrayDataSource qrcodeDataSource = new ByteArrayDataSource(Base64.getDecoder().decode(qrcodeData), "image/png");

        // Add the QR code as an attachment
        helper.addAttachment("qrcode.png", qrcodeDataSource);
        // Add the PDF file download link to the email body
        String emailBody = "<html><body><p>Please find attached the PDF file.</p>" +
                "<p>You can also download the PDF file by scanning the QR code below:</p>" +
                "<img src='data:image/png;base64," + qrcodeData + "' alt='QR Code' width='150' height='150'>" +
                "</body></html>";
        helper.setText(emailBody, true);
        mailSender.send(message);
    }}
