package Models;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.awt.Desktop;
import java.io.File;

public class PdfManager {

    public String readPdf(String pathPdf){
try {
    File file = new File(pathPdf);
    if(file.exists()) {
          String[] pathSplit = pathPdf.split("/");
          String extension =pathSplit[pathSplit.length-1].split("\\.")[1];
        if(extension.equals("pdf")){
            PDDocument pdf = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(pdf);
            pdf.close();
            return text;
        }
        else{
            return "Not a pdf";
        }
    }
   else{
       return "File path is invalid";
    }

}
catch (Exception e){
    e.printStackTrace();
    return "Error";
}
    }
    public String openPdf(String pathPdf){
        try {

            File file = new File(pathPdf);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                    return "Done";
                } else {
                    return "Awt Desktop is not supported!";
                }

            } else {
                return "File is not exists!";
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error";
        }

    }


}


