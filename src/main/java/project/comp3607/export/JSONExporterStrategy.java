package project.comp3607.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class JSONExporterStrategy implements ExporterStrategy {

    @Override
    public File export(Object o) throws Exception {
        // Define the output file
        File file = new File("export.json");

        try (FileWriter writer = new FileWriter(file)) {
            // NOTE: This is a placeholder.
            // In a real app, you would use a library like Gson or Jackson
            // to convert the object 'o' into a proper JSON string.
            writer.write(o.toString());
        } catch (IOException e) {
            throw new Exception("Failed to write JSON file", e);
        }

        return file;
    }
}
