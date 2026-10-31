package project.comp3607.events;

import java.time.Instant;

public class SessionEndEvent extends TelemetryEventBase {

    public SessionEndEvent(int sequence, Instant timeStamp) {
        super(sequence, timeStamp, EventType.SESSION_END);
    }

}
