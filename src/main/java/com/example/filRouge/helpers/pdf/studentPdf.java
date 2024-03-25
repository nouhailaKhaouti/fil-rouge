package com.example.filRouge.helpers.pdf;

import com.example.filRouge.entities.Inscription;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RequiredArgsConstructor
public class studentPdf {
    private final List<Inscription> inscriptionList;


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Student Cin", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Student Cne", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Inscription user : inscriptionList) {
            table.addCell(user.getCin());
            table.addCell(user.getCne());
            table.addCell(user.getNom());
            table.addCell(user.getPrenom());
            table.addCell(user.getEmail());
        }
    }

    public void export(HttpServletResponse response,String title) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph(title, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }

    public static byte[] generateTicketPdf(List<Inscription> inscriptionsList,String title) throws Exception {
        // Load the JasperReport template from resources
        InputStream templateStream = studentPdf.class.getClassLoader().getResourceAsStream("inscriptionList.jrxml");
        if (templateStream == null)
            throw new FileNotFoundException("Template not found in the classpath");

        JasperDesign jasperDesign = JRXmlLoader.load(templateStream);

        // Compile the JasperReport template
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        // Prepare parameters
        Map<String, Object> parameters = new HashMap<>();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inscriptionsList);
        parameters.put("inscriptionsList", dataSource);
        parameters.put("title", title);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        // Export to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}