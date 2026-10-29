package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class CompileErrorEvent extends TelemetryEventBase implements DetailsInterface {

    private final String file;
    private final int line;
    private final String message;
    private final ErrorCategory category;

    public CompileErrorEvent(
        int sequence, Instant timestamp, String file,
        int line, String message, ErrorCategory category
    ) {
        super(sequence, timestamp, EventType.COMPILE_ERROR);
        this.file = file;
        this.line = line;
        this.message = message;
        this.category = category;
    }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("file", file);
        details.put("line", line);
        details.put("message", message);
        details.put("category", category.getCode());
        return details;
    }


}
