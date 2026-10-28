package project.comp3607.plugin;

import bluej.extensions2.Extension;
import bluej.extensions2.BlueJ;

public class TelemetryExtension extends Extension {
    @Override
    public void startup(BlueJ bluej) {
        bluej.setMenuGenerator(new TelemetryMenu());
    }

    @Override
    public boolean isCompatible() {
        return true;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getName() {
        return "Telemetry Extension";
    }

    public String getDescription() {
        return "Collects BlueJ session telemetry";
    }

}
