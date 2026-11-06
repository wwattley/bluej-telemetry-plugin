package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassCreatedEvent extends TelemetryEventBase {
    private String project;
    private String className;

    public ClassCreatedEvent(int sequence, Instant timeStamp, String project, String className) {
        super(sequence, timeStamp, EventType.CLASS_CREATED);
        this.project = project;
        this.className = className;
    }

    public String getProject() { return project; }
    public String getClassName() { return className; }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("project", project);
        details.put("class_name", className);
        return details;
    }


}
