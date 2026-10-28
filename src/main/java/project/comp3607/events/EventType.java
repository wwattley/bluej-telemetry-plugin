package project.comp3607.events;

public enum EventType {
    SESSION_START("SS"),
    SESSION_END("SE"),
    PROJECT_OPENED("PO"),
    PROJECT_CLOSED("PC"),
    CLASS_CREATED("CC"),
    CLASS_DELETED("CD"),
    COMPILE_SUCCEEDED("CS"),
    COMPILE_FAILED("CF"),
    COMPILE_ERROR("CE"),
    RUN_COMPLETED("RC");

    private final String code;

    private EventType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // PDF Exporter uses this to parse the codes stored in the JSON back to their enums later
    public static EventType fromCode(String code) {
        for (EventType t : EventType.values()) {
            if(t.code.equals(code)) return t;
        }
        throw new IllegalArgumentException("Unknown event code: " + code);
    }

}
