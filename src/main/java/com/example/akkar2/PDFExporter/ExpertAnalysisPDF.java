package com.example.akkar2.PDFExporter;

import com.example.akkar2.QrCode.CodeGenerator;
import com.example.akkar2.entities.ExpertAnalysis;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class ExpertAnalysisPDF {
    private ExpertAnalysis expertAnalysis;
    public ExpertAnalysisPDF(ExpertAnalysis expertAnalysis) {
        this.expertAnalysis = expertAnalysis;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("priceprediction", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("insepctiondate", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("pictures", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table) {
        table.addCell(String.valueOf(expertAnalysis.getPriceprediction()));
        table.addCell(expertAnalysis.getInsepctiondate().toString());
        table.addCell(expertAnalysis.getDescription());
        table.addCell(expertAnalysis.getPictures());

    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Expert Analysis Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();


    }
}
