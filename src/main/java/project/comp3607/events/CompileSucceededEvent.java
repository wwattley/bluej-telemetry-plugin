package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class CompileSucceededEvent extends TelemetryEventBase {
    private final List<String> files;

    public CompileSucceededEvent(int sequence, Instant timestamp, List<String> files) {
        super(sequence, timestamp, EventType.COMPILE_SUCCEEDED);
        this.files = List.copyOf(files);
    }

    public List<String> getFiles() { return files; }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("files", files);
        return details;
    }

}
