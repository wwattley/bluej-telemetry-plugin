package project.comp3607.events;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class RunCompletedEvent extends TelemetryEventWithDetail {
    private final String className;
    private final String methodName; // null for object creation
    private final boolean exception;
    private final String result;

    public RunCompletedEvent(
        int sequence, Instant timeStamp, String className,
        String methodName, boolean exception, String result
    ) {
        super(sequence, timeStamp, EventType.RUN_COMPLETED);
        this.className = className;
        this.methodName = methodName;
        this.exception = exception;
        this.result = result;
    }

    public String getClassName() { return className; }
    public String getMethodName() { return methodName; }
    public boolean isException() { return exception; }
    public String getResult() { return result; }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("className", className);
        details.put("methodName", methodName);
        details.put("exception", exception);
        details.put("result", result);
        return details;
    }

}
