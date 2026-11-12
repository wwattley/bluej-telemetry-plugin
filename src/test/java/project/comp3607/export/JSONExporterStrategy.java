package project.comp3607.export;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class JSONExporterStrategy {

    @TempDir
    Path tempDir; // JUnit will automatically create and clean up a temp directory

    @Test
    public void testExportCreatesValidJsonStructure() throws Exception {
        // 1. Arrange
        JSONExporterStrategy strategy = new JSONExporterStrategy();

        // Create a simple dummy object to serialize
        Object dummyData = new Object() {
            public String student_id = "12345";
            public int duration_seconds = 300;
        };

        // 2. Act
        File resultFile = strategy.export(dummyData);

        // 3. Assert
        assertNotNull(resultFile, "The exported file should not be null");
        assertTrue(resultFile.exists(), "The exported file should exist on disk");

        // Read the file contents
        String jsonContent = new String(Files.readAllBytes(resultFile.toPath()));

        // Verify it is syntactically valid JSON
        assertDoesNotThrow(() -> JsonParser.parseString(jsonContent),
            "The output should be a valid JSON format");

        // Verify specific data keys are present
        assertTrue(jsonContent.contains("student_id"), "JSON should contain student_id");
        assertTrue(jsonContent.contains("12345"), "JSON should contain the correct ID");
    }
}
