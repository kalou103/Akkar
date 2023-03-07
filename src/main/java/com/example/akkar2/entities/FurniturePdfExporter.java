package com.example.akkar2.entities;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FurniturePdfExporter {

    public void export(List<Furniture> furnitureList, HttpServletResponse response) throws DocumentException, IOException {
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
        Paragraph paragraph1 = new Paragraph("List of Furnitures", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(4);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 1.5f, 1.5f});
        table.setSpacingBefore(10);
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
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Stock", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
        // Iterating the list of furniture
        for (Furniture furniture : furnitureList) {
            // Adding furniture ID
            table.addCell(String.valueOf(furniture.getFurnitureId()));
            // Adding furniture Name
            table.addCell(furniture.getFurnitureName());
            // Adding furniture Quantity
            table.addCell(String.valueOf(furniture.getStock()));
            // Adding furniture Price
            table.addCell(String.valueOf(furniture.getFurniturePrice()));
        }
        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();
    }
}
