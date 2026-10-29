package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassDeletedEvent extends TelemetryEventBase implements DetailsInterface {
    private final String project;
    private final String className;

    public ClassDeletedEvent(int sequence, Instant timeStamp, String project, String className) {
        super(sequence, timeStamp, EventType.CLASS_DELETED);
        this.project = project;
        this.className = className;
    }

    public String getProject() { return project; }
    public String getClassName() { return className; }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("project", project);
        details.put("className", className);
        return details;
    }

}
