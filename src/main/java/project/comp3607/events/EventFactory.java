package project.comp3607.events;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class EventFactory {
    private final Clock clock;
    private final AtomicInteger nextSequence = new AtomicInteger(1);

    public EventFactory() {
        this(Clock.systemUTC());
    }

    public EventFactory(Clock clock) {
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    private int getNextSequence() {
        return nextSequence.getAndIncrement();
    }

    private Instant getCurrentTime() {
        return clock.instant();
    }

    public TelemetryEventBase sessionStart() {
        return new SessionStartEvent(getNextSequence(), getCurrentTime());
    }

    public TelemetryEventBase sessionEnd() {
        return new SessionEndEvent(getNextSequence(), getCurrentTime());
    }

    public TelemetryEventBase projectOpened(String project) {
        return new ProjectOpenedEvent(getNextSequence(), getCurrentTime(), project);
    }

    public TelemetryEventBase projectClosed(String project) {
        return new ProjectClosedEvent(getNextSequence(), getCurrentTime(), project);
    }

    public TelemetryEventBase classCreated(String project, String className) {
        return new ClassCreatedEvent(getNextSequence(), getCurrentTime(), project, className);
    }

    public TelemetryEventBase classDeleted(String project, String className) {
        return new ClassDeletedEvent(getNextSequence(), getCurrentTime(), project, className);
    }

    public TelemetryEventBase edit(String project, String className) {
        return new EditEvent(getNextSequence(), getCurrentTime(), project, className);
    }

    public TelemetryEventBase compileSucceeded(List<String> files) {
        return new CompileSucceededEvent(getNextSequence(), getCurrentTime(), files);
    }

    public TelemetryEventBase compileFailed(List<String> files) {
        return new CompileFailedEvent(getNextSequence(), getCurrentTime(), files);
    }

    public TelemetryEventBase compileError(
        String file, int line, String message, ErrorCategory category
    ) {
        return new CompileErrorEvent(
            getNextSequence(), getCurrentTime(), file, line, message, category
        );
    }

    public TelemetryEventBase runCompleted(
        String className, String methodName, boolean exception, String result
    ) {
        return new RunCompletedEvent(
            getNextSequence(), getCurrentTime(), className, methodName, exception, result
        );
    }
}
