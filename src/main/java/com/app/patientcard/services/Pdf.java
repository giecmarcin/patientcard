package com.app.patientcard.services;

import com.app.patientcard.entities.Medicine;
import com.app.patientcard.entities.Observation;
import com.app.patientcard.entities.Patient;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class Pdf {
    private Font font;
    private Patient patient;
    private String FILE;
    private BaseFont baseFont;
    //private Font font;
    private Document document;
    private PdfWriter writer;


    public Pdf(Patient patient) {
        Random random = new Random();
        this.document = new Document(PageSize.A4.rotate(), 3f, 1f, 35f, 0f);
        this.patient = patient;
        String fileName = LocalDate.now().toString().replaceAll(":","") + "-" + patient.getId() + "-" + "-"+patient.getFirstName() + "_" +patient.getLastName() + "_" + random.nextInt();
        this.FILE = System.getProperty("user.dir") + "/" + fileName + ".pdf";
        String OS = System.getProperty("os.name").toLowerCase();
        //Uncomment on windows
        try {
            this.baseFont = BaseFont.createFont("/home/marcin/Fonts/arial.ttf",
                    BaseFont.CP1250, BaseFont.EMBEDDED);

            this.font = new Font(baseFont, 9);
        } catch (DocumentException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }


    public void create() throws FileNotFoundException, DocumentException {
        this.writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addContent();
        document.close();
        openPDFLinux(FILE);
    }

    private void addContent() throws DocumentException {
        Anchor anchor = new Anchor("Patient", font);
        anchor.setName("Patient");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Patient data:", font);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("First name: " + patient.getFirstName(),font));
        subCatPart.add(new Paragraph("Last name: " + patient.getLastName(),font));
        subCatPart.add(new Paragraph("Date of birth: " + patient.getDayOfBirth().toString(), font));



        // add a list
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);
        subCatPart.add(Chunk.NEWLINE);
        // add a table
        createTable(subCatPart);
        subCatPart.add(Chunk.NEWLINE);
        subCatPart.add(new Paragraph("Medicines: "));
        createTableMedicines(subCatPart);
        document.add(catPart);
    }

    //Observations
    private void createTable(Section subCatPart)
            throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        PdfPCell c1 = new PdfPCell(new Phrase("Id",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Day",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Description",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Observation item : patient.getObservations()) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(item.getId()),font)));
            table.addCell(new PdfPCell(new Phrase(item.getZonedDateTime().toString(),font)));
            table.addCell(new PdfPCell(new Phrase(item.getDescription(),font)));
        }
        subCatPart.add(table);
    }

    //Medicines
    private void createTableMedicines(Section subCatPart)
            throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        PdfPCell c1 = new PdfPCell(new Phrase("Id",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Day",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Name",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity",font));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Medicine item : patient.getMedicines()) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(item.getId()),font)));
            table.addCell(new PdfPCell(new Phrase(item.getZonedDateTime().toString(),font)));
            table.addCell(new PdfPCell(new Phrase(item.getName(),font)));
            table.addCell(new PdfPCell(new Phrase(item.getQuantity(),font)));
        }
        subCatPart.add(table);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void openPDF(String filePath) {
        try {

            if ((new File(filePath)).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler " + filePath);
                p.waitFor();

            } else {

                System.out.println("File is not exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            //ex.printStackTrace();
            openPDFLinux(filePath);
        }
    }

    public void openPDFLinux(String filePath) {
        try {

            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
}