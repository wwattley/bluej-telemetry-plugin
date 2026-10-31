package project.comp3607.events;

public enum ErrorCategory {
    SYMBOL("SYM"),
    TYPE("TYP"),
    SYNTAX("SYN"),
    BRACES("BRC"),
    MISSING_RETURN("RET"),
    OTHER("OTH");

    private final String code;

    private ErrorCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // PDF Exporter uses this to parse the codes stored in the JSON back to their enums later
    public static ErrorCategory fromCode(String code) {
        for (ErrorCategory c : ErrorCategory.values()) {
            if(c.code.equals(code)) return c;
        }
        throw new IllegalArgumentException("No ErrorCategory found for code: " + code);
    }
}
