package project.comp3607.events;

import java.time.Instant;

public class SessionStartEvent extends TelemetryEventBase {

    public SessionStartEvent(int sequence, Instant timeStamp, String project, String user) {
        super(sequence, timeStamp, EventType.SESSION_START);
    }

}
