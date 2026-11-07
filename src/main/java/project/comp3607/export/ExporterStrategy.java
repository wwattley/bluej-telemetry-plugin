//package project.comp3607.export

import java.lang.Object;
import java.io.File;
public interface ExporterStrategy{



    public File export(Object snapshot) throws Exception;
}
