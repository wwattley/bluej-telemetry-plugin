package project.comp3607.events;

import java.time.Instant;
import java.util.Map;

public abstract class TelemetryEventWithDetail extends TelemetryEventBase {
    protected TelemetryEventWithDetail(int sequence, Instant timeStamp, EventType type) {
        super(sequence, timeStamp, type);
    }

    // Fields that go into the JSON details object in insertion order.
    public abstract Map<String, Object> getDetails();

}
