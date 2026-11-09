package project.comp3607.export;

import java.io.File;
public interface ExporterStrategy{

    String getFileExtension(); //type of file extension pdf or json

    public File export(Object snapshot) throws Exception;

}
