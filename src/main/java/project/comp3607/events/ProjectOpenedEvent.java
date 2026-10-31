package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectOpenedEvent extends TelemetryEventBase {
    private final String project;

    public ProjectOpenedEvent(int sequence, Instant timeStamp, String project) {
        super(sequence, timeStamp, EventType.PROJECT_OPENED);
        this.project = project;
    }

    public String getProject() { return project; }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("project", project);
        return details;
    }

}
