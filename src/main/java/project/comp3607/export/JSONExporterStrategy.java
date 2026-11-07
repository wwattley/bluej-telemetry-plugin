package project.comp3607.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class JSONExporterStrategy implements ExporterStrategy {

    @Override
    public File export(Object o) throws Exception {
        // Define the output file
        File file = new File("export.json");
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(file)) {
            // convert the object to a proper JSON string
            gson.toJson(o, writer);
        } catch (IOException e) {
            throw new Exception("Failed to write JSON file", e);
        }

        return file;
    }
}
