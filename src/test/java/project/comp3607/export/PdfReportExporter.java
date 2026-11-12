package project.comp3607.export;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class PdfReportExporter {

    @TempDir
    Path tempDir;

    @Test
    public void testPdfGenerationWithAggregatedMath() throws Exception {
        // 1. Arrange - Mock the complex nested objects
        PdfReportExporter exporter = new PdfReportExporter();

        // Mock Session
        Session mockSession = mock(Session.class);
        mockSession.student_id = "STU-999";
        mockSession.duration_seconds = 600;

        // Mock Summary (Simulating the Aggregator Math)
        Summary mockSummary = mock(Summary.class);
        mockSummary.active_Seconds = 450; // Math: 450 active
        mockSummary.idle_Seconds = 150;   // Math: 150 idle (Total 600)

        // Simulating error frequency calculations
        Map<String, Integer> errorFreq = new HashMap<>();
        errorFreq.put("Syntax Error", 5);
        errorFreq.put("Type Error", 2);
        mockSummary.error_frequency = errorFreq;

        // Mock Events
        EventRecord mockEvent = mock(EventRecord.class);
        mockEvent.seq = 1;
        mockEvent.type = "COMPILE_SUCCESS";
        mockEvent.timestamp = "12:00:01";

        // Mock the main Report object
        TelemetryReport mockReport = mock(TelemetryReport.class);
        mockReport.session = mockSession;
        mockReport.summary = mockSummary;
        mockReport.events = List.of(mockEvent);

        // 2. Act
        // Note: Your PdfReportExporter currently hardcodes "report.pdf".
        // For testing, it's better if you pass the output path, but we'll test as is.
        File generatedPdf = exporter.export(mockReport);

        // 3. Assert
        assertNotNull(generatedPdf, "PDF file should be generated");
        assertTrue(generatedPdf.exists(), "PDF file should exist");
        assertTrue(generatedPdf.length() > 0, "PDF file should contain data");

        // Verify Mockito interactions (Optional, but ensures your code reads the math)
        // E.g., we can verify it actually touched the error_frequency map
        verify(mockReport, atLeastOnce()).summary(); // Assuming you use getters
    }
}
