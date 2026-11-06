package project.comp3607.processing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import project.comp3607.events.ErrorCategory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompileErrorCategorizerTest {
    private final CompileErrorCategorizer categorizer = new CompileErrorCategorizer();

    static Stream<Arguments> javacMessages() {
        return Stream.of(
            Arguments.of("cannot find symbol", ErrorCategory.SYMBOL),
            Arguments.of("package java.util.missing does not exist", ErrorCategory.SYMBOL),
            Arguments.of("incompatible types: int cannot be converted to String", ErrorCategory.TYPE),
            Arguments.of("method bark() in class Dog cannot be applied to given types", ErrorCategory.TYPE),
            Arguments.of("bad operand types for binary operator '+'", ErrorCategory.TYPE),
            Arguments.of("unreported exception java.io.IOException; must be caught or declared", ErrorCategory.TYPE),
            Arguments.of("';' expected", ErrorCategory.SYNTAX),
            Arguments.of("illegal start of expression", ErrorCategory.SYNTAX),
            Arguments.of("class, interface, enum, or record expected", ErrorCategory.SYNTAX),
            Arguments.of("not a statement", ErrorCategory.SYNTAX),
            Arguments.of("reached end of file while parsing", ErrorCategory.BRACES),
            Arguments.of("missing return statement", ErrorCategory.MISSING_RETURN),
            Arguments.of("some completely different compiler complaint", ErrorCategory.OTHER)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("javacMessages")
    void categorizesRawCompilerMessages(String message, ErrorCategory expected) {
        assertEquals(expected, categorizer.categorize(message));
    }

    @Test
    void normalizesCaseAndWhitespaceBeforeMatching() {
        assertEquals(
            ErrorCategory.SYMBOL,
            categorizer.categorize("  CANNOT\tFIND\nSYMBOL  ")
        );
    }

    @Test
    void emptyMissingAndBlankMessagesAreOther() {
        assertEquals(ErrorCategory.OTHER, categorizer.categorize(null));
        assertEquals(ErrorCategory.OTHER, categorizer.categorize(""));
        assertEquals(ErrorCategory.OTHER, categorizer.categorize("   "));
    }
}
