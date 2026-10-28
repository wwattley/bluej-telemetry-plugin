package project.comp3607.plugin;

import bluej.extensions2.MenuGenerator;
import bluej.extensions2.BPackage;
import javafx.scene.control.MenuItem;


public class TelemetryMenu extends MenuGenerator {

    @Override
    public MenuItem getToolsMenuItem(BPackage bPackage) {
        MenuItem item = new MenuItem();
        item.setText("Export Telemetry");
        return item;
    }
}
