package project.comp3607.processing;

import project.comp3607.events.ErrorCategory;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Maps a raw javac error message to one of the categories defined by the
 * Build Contract.
 */
public final class CompileErrorCategorizer {
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");

    public ErrorCategory categorize(String message) {
        if (message == null || message.isBlank()) {
            return ErrorCategory.OTHER;
        }

        String normalized = WHITESPACE.matcher(message)
            .replaceAll(" ")
            .trim()
            .toLowerCase(Locale.ROOT);

        if (normalized.contains("reached end of file while parsing")) {
            return ErrorCategory.BRACES;
        }

        if (normalized.contains("missing return statement")) {
            return ErrorCategory.MISSING_RETURN;
        }

        if (normalized.contains("cannot find symbol")
            || normalized.contains("cannot resolve")
            || (normalized.contains("package") && normalized.contains("does not exist"))) {
            return ErrorCategory.SYMBOL;
        }

        if (normalized.contains("incompatible types")
            || normalized.contains("incompatible operand")
            || normalized.contains("cannot be applied")
            || normalized.contains("no suitable method")
            || normalized.contains("bad operand types")
            || normalized.contains("unreported exception")
            || normalized.contains("possible lossy conversion")
            || normalized.contains("cannot be converted")) {
            return ErrorCategory.TYPE;
        }

        if (normalized.contains("expected")
            || normalized.contains("illegal start")
            || normalized.contains("not a statement")
            || normalized.contains("malformed")) {
            return ErrorCategory.SYNTAX;
        }

        return ErrorCategory.OTHER;
    }
}
