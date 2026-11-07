package project.comp3607.export;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientContext {

    @Test
    public void testExecuteExportThrowsExceptionIfNoStrategySet() {
        ClientContext context = new ClientContext();

        // Assert that calling export without a strategy throws an exception
        assertThrows(IllegalStateException.class, () -> {
            context.executeExport(new Object());
        });
    }

    @Test
    public void testExecuteExportCallsStrategy() throws Exception {
        ClientContext context = new ClientContext();

        // Create a dummy strategy that tracks if it was called
        class SpyStrategy implements ExporterStrategy {
            boolean wasCalled = false;
            @Override
            public java.io.File export(Object snapshot) throws Exception {
                wasCalled = true;
                return new java.io.File("dummy.txt");
            }
        }

        SpyStrategy spy = new SpyStrategy();
        context.setStrategy(spy);

        context.executeExport("Test Data");

        assertTrue(spy.wasCalled, "The context should have triggered the strategy's export method");
    }
}
