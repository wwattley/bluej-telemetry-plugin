package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectClosedEvent extends TelemetryEventBase implements DetailsInterface {
    private final String project;

    public ProjectClosedEvent(int sequence, Instant timeStamp, String project) {
        super(sequence, timeStamp, EventType.PROJECT_CLOSED);
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
