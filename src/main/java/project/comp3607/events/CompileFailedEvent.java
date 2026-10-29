package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class CompileFailedEvent extends TelemetryEventWithDetail {
    private final List<String> files;

    public CompileFailedEvent(int sequence, Instant timestamp, List<String> files) {
        super(sequence, timestamp, EventType.COMPILE_FAILED);
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
