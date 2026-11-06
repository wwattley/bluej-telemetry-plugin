package project.comp3607.events;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

public abstract class TelemetryEventBase implements DetailsInterface {
    private final int sequence;
    private final Instant timeStamp;
    private final EventType type;

    protected TelemetryEventBase(int sequence, Instant timeStamp, EventType type) {
        this.sequence = sequence;
        this.timeStamp = timeStamp;
        this.type = type;
    }

    public int getSequence() { return sequence; }
    public Instant getTimeStamp() { return timeStamp; }
    public EventType getEventType() { return type; }

    @Override
    public Map<String, Object> getDetails() {
        return Collections.emptyMap();
    }

}
