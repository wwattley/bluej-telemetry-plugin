package project.comp3607.export;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class clientContext {

    @Test
    public void testExecuteExportThrowsExceptionIfNoStrategySet() {
        clientContext context = new clientContext();

        // Assert that calling export without a strategy throws an exception
        assertThrows(IllegalStateException.class, () -> {
            context.executeExport(new Object());
        });
    }

    @Test
    public void testExecuteExportCallsStrategy() throws Exception {
        clientContext context = new clientContext();

        // Create a dummy strategy that tracks if it was called
        class SpyStrategy implements ExporterStrategy {
            boolean wasCalled = false;
            @Override
            public java.io.File export(Object snapshot) throws Exception {
                wasCalled = true;
                return new java.io.File("dummy.txt");
            }
            @Override
            public String getFileExtension() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getFileExtension'");
            }
        }

        SpyStrategy spy = new SpyStrategy();
        context.setStrategy(spy);

        context.executeExport("Test Data");

        assertTrue(spy.wasCalled, "The context should have triggered the strategy's export method");
    }
}
