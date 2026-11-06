package project.comp3607.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTypeTest {

    @Test
    void everyCodeMapsBackToItsEventType() {
        for (EventType type : EventType.values()) {
            assertEquals(type, EventType.fromCode(type.getCode()));
        }
    }

    @Test
    void editUsesTheEdCode() {
        assertEquals("ED", EventType.EDIT.getCode());
    }

    @Test
    void activityTypesAreMarkedAsActivity() {
        assertTrue(EventType.EDIT.isActivity());
        assertTrue(EventType.COMPILE_SUCCEEDED.isActivity());
        assertTrue(EventType.COMPILE_FAILED.isActivity());
        assertTrue(EventType.COMPILE_ERROR.isActivity());
        assertTrue(EventType.RUN_COMPLETED.isActivity());
        assertTrue(EventType.CLASS_CREATED.isActivity());
        assertTrue(EventType.CLASS_DELETED.isActivity());
    }

    @Test
    void sessionAndProjectTypesAreNotActivity() {
        assertFalse(EventType.SESSION_START.isActivity());
        assertFalse(EventType.SESSION_END.isActivity());
        assertFalse(EventType.PROJECT_OPENED.isActivity());
        assertFalse(EventType.PROJECT_CLOSED.isActivity());
    }

    @Test
    void unknownCodeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> EventType.fromCode("??"));
    }
}
