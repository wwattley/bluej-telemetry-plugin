package project.comp3607.export;

public class clientContext {

        private ExporterStrategy exportStrategy;


        public void setStrategy(ExporterStrategy exportStrategy){
            this.exportStrategy = exportStrategy;
        };

        public void executeExport(Object data) throws Exception{

            if (exportStrategy == null){
                throw new IllegalStateException("Export strategy not set!");
            }
            exportStrategy.export(data);
        }



}
