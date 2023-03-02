package com.example.akkar2.entities;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;


public class GeneratePdf {
    public void generate(List < Command > commandList, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("List of the commands", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(4);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3,3});
        table.setSpacingBefore(5);
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);
        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        // Adding headings in the created table cell or  header
        // Adding Cell to table
        cell.setPhrase(new Phrase("commandId", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("furnitures", font));
        table.addCell(cell);
       // cell.setPhrase(new Phrase("Mobile No", font));
       // table.addCell(cell);
        // Iterating the list of students
        for (Command command: commandList) {
            // Adding student id
            table.addCell(String.valueOf(command.getCommandId()));
            // Adding student name
            table.addCell(String.valueOf(command.getCommandDate()));
            // Adding student email
            table.addCell(String.valueOf(command.getFurnitures()));
            // Adding student mobile
            //table.addCell(student.getMobileNo());
        }
        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();
    }
}
