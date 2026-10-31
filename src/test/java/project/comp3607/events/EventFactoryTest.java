package project.comp3607.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventFactoryTest {
    private static final Instant NOW = Instant.parse("2026-11-01T10:00:00Z");

    private EventFactory factory;

    @BeforeEach
    void setUp() {
        factory = new EventFactory(Clock.fixed(NOW, ZoneOffset.UTC));
    }

    @Test
    void assignsSequentialNumbersAndTheSameTimestamp() {
        List<TelemetryEventBase> events = List.of(
            factory.sessionStart(),
            factory.projectOpened("DogProject"),
            factory.classCreated("DogProject", "Dog"),
            factory.edit("DogProject", "Dog"),
            factory.compileError("Dog.java", 3, "cannot find symbol", ErrorCategory.SYMBOL),
            factory.compileSucceeded(List.of("Dog.java")),
            factory.compileFailed(List.of("Dog.java")),
            factory.runCompleted("Dog", null, false, "woof"),
            factory.classDeleted("DogProject", "Dog"),
            factory.projectClosed("DogProject"),
            factory.sessionEnd()
        );

        for (int i = 0; i < events.size(); i++) {
            assertEquals(i + 1, events.get(i).getSequence());
            assertEquals(NOW, events.get(i).getTimeStamp());
        }
    }

    @Test
    void sessionEventsHaveEmptyDetails() {
        assertTrue(factory.sessionStart().getDetails().isEmpty());
        assertTrue(factory.sessionEnd().getDetails().isEmpty());
    }

    @Test
    void sessionEndKeepsItsTimestamp() {
        assertEquals(NOW, factory.sessionEnd().getTimeStamp());
    }

    @Test
    void projectAndClassEventsUseSnakeCaseKeys() {
        assertEquals(
            Map.of("project", "DogProject"),
            factory.projectOpened("DogProject").getDetails()
        );
        assertEquals(
            Map.of("project", "DogProject"),
            factory.projectClosed("DogProject").getDetails()
        );
        assertEquals(
            Map.of("project", "DogProject", "class_name", "Dog"),
            factory.classCreated("DogProject", "Dog").getDetails()
        );
        assertEquals(
            Map.of("project", "DogProject", "class_name", "Dog"),
            factory.classDeleted("DogProject", "Dog").getDetails()
        );
        assertEquals(
            Map.of("project", "DogProject", "class_name", "Dog"),
            factory.edit("DogProject", "Dog").getDetails()
        );
    }

    @Test
    void compileEventsKeepAnImmutableCopyOfTheFiles() {
        List<String> files = new ArrayList<>(List.of("Dog.java"));

        TelemetryEventBase succeeded = factory.compileSucceeded(files);
        TelemetryEventBase failed = factory.compileFailed(files);
        files.add("Cat.java");

        assertEquals(Map.of("files", List.of("Dog.java")), succeeded.getDetails());
        assertEquals(Map.of("files", List.of("Dog.java")), failed.getDetails());
    }

    @Test
    void compileErrorStoresFileLineMessageAndCategoryCode() {
        CompileErrorEvent event = (CompileErrorEvent) factory.compileError(
            "Dog.java", 3, "cannot find symbol", ErrorCategory.SYMBOL
        );

        assertEquals("Dog.java", event.getFile());
        assertEquals(3, event.getLine());
        assertEquals("cannot find symbol", event.getMessage());
        assertEquals(ErrorCategory.SYMBOL, event.getCategory());
        assertEquals(
            Map.of(
                "file", "Dog.java",
                "line", 3,
                "message", "cannot find symbol",
                "category", "SYM"
            ),
            event.getDetails()
        );
    }

    @Test
    void runCompletedStoresSnakeCaseKeysAndAllowsANullMethodName() {
        RunCompletedEvent event = (RunCompletedEvent) factory.runCompleted(
            "Dog", null, false, "woof"
        );

        Map<String, Object> details = event.getDetails();
        assertEquals("Dog", details.get("class_name"));
        assertTrue(details.containsKey("method_name"));
        assertNull(details.get("method_name"));
        assertEquals(false, details.get("exception"));
        assertEquals("woof", details.get("result"));
    }

    @Test
    void runResultIsTruncatedToTwoHundredCharacters() {
        RunCompletedEvent event = (RunCompletedEvent) factory.runCompleted(
            "Dog", "bark", false, "x".repeat(250)
        );

        assertEquals(200, event.getResult().length());
        assertEquals(event.getResult(), event.getDetails().get("result"));
    }

    @Test
    void runResultIsNotChangedWhenItIsWithinTheLimit() {
        String result = "x".repeat(200);
        RunCompletedEvent event = (RunCompletedEvent) factory.runCompleted(
            "Dog", "bark", false, result
        );

        assertEquals(result, event.getResult());
    }

    @Test
    void nullRunResultBecomesAnEmptyString() {
        RunCompletedEvent event = (RunCompletedEvent) factory.runCompleted(
            "Dog", "bark", true, null
        );

        assertEquals("", event.getResult());
    }
}
