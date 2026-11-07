package project.comp3607.export;

public class testApp {

    public static void main(String[] args) {

    clientContext ctx = new clientContext();

        //or  new PDFReportExporter
    ctx.setStrategy(new JSONExporterStrategy());
    try{
        ctx.executeExport("datadatadata");
        System.out.println("Export Successful");
    }
    catch(Exception e){
        System.out.println(e);
    }
    }



}
