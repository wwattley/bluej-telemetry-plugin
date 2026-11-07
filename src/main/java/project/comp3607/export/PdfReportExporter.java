package project.comp3607.export;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Map;





//implement the interface wiht openPDF and JChart to build the styled pages, tables and headers

public class PdfReportExporter implements ExporterStrategy {

    public void export(Object snapshot) throws Exception {
        //TelemetryReport may have to change
        TelemetryReport jsonReport = (TelemetryReport) snapshot;
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        // 1. Title and Session Metadata
        document.add(new Paragraph("BlueJ Session Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph("Student ID: " + jsonReport.session.student_id));
        document.add(new Paragraph("Duration: " + jsonReport.session.duration_seconds + " seconds"));
        document.add(Chunk.NEWLINE);

        // 2. Active vs Idle Time (Pie Chart)
        DefaultPieDataset<String> timeDataset = new DefaultPieDataset<>();
        timeDataset.setValue("Active", jsonReport.summary.active_Seconds);
        timeDataset.setValue("Idle", jsonReport.summary.idle_Seconds);

        JFreeChart pieChart = ChartFactory.createPieChart("Time Distribution", timeDataset);
        BufferedImage pieImage = pieChart.createBufferedImage(400, 300);

        com.lowagie.text.Image pdfPieImage = com.lowagie.text.Image.getInstance(pieImage, null);
        document.add(pdfPieImage);

        // 3. Error Frequency (Table)
        document.add(new Paragraph("Compilation Error Breakdown"));
        PdfPTable errorTable = new PdfPTable(2); // 2 columns: Category, Count
        errorTable.addCell("Error Category");
        errorTable.addCell("Frequency");

        for (Map.Entry<String, Integer> entry :jsonReport.summary.error_frequency.entrySet()) {
            errorTable.addCell(entry.getKey());
            errorTable.addCell(String.valueOf(entry.getValue()));
        }
        document.add(errorTable);
        PdfPTable timelineTable = new PdfPTable(3); // Seq, Type, Timestamp
        timelineTable.addCell("Sequence");
        timelineTable.addCell("Type");
        timelineTable.addCell("Timestamp");

        // 4. Progression Timeline (Table of Events)
        document.add(new Paragraph("Event Timeline"));

        for (EventRecord event : jsonReport.events) {
            timelineTable.addCell(String.valueOf(event.seq));
            timelineTable.addCell(event.type);
            timelineTable.addCell(event.timestamp);
        }
        document.add(timelineTable);

        document.close();
    }
}
