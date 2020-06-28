package Models;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;

public class PdfManager {

    public String readPdfPage(PDDocument pdf,PDFTextStripper pdfStripper,int pageNumber){
        try {
            pdfStripper.setStartPage(pageNumber);
            pdfStripper.setEndPage(pageNumber);

            String text = pdfStripper.getText(pdf);
            return text;

        }
        catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    public ArrayList<String> readPdf(String pathPdf) {
        ArrayList<String> contentArrayList=new ArrayList<String>();
        try{
            File file = new File(pathPdf);
            if (file.exists()) {
                String[] pathSplit = pathPdf.split("/");
                String extension = pathSplit[pathSplit.length - 1].split("\\.")[1];
                if (extension.equals("pdf")) {
                    PDDocument pdf = PDDocument.load(file);
                    PDFTextStripper pdfStripper = new PDFTextStripper();
                    for(int pageNumber=1;pageNumber<=pdf.getNumberOfPages();pageNumber++){
                        String pageContent=String.join(" ",readPdfPage(pdf,pdfStripper, pageNumber).split("\\r?\\n"));
                        contentArrayList.add(pageContent);
                    }
                    pdf.close();
                    return contentArrayList;
                } else {
                    System.out.println("Not a PDF");
                    ArrayList<String> returnMsg = new ArrayList<String>();
                    returnMsg.add("Not a PDF");
                    return returnMsg;

                }
        } else {
            System.out.println("File path is invalid");
            ArrayList<String> returnMsg = new ArrayList<String>();
            returnMsg.add("File path is invalid");
            return returnMsg;
        }
    }
    catch (Exception e){
                e.printStackTrace();
            return  null;
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


