package Models;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PdfManagerTest {
    //openPdf -- file exists
    @Test
    public void openPdfTest1() {
        PdfManager pdfManager = new PdfManager();

        assertEquals("Done",pdfManager.openPdf("./pdf/CS2022_L01_Introduction.pdf"));
    }

    //openPdf -- file not exists
    @Test
    public void openPdfTest2() {
        PdfManager pdfManager = new PdfManager();

        assertEquals("File is not exists!",pdfManager.openPdf("./pdf/CS2022_L01.pdf"));
    }

    //readPdf -- "File path is invalid"
    @Test
    public void readPdfTest1(){
        PdfManager pdfManager = new PdfManager();

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("File path is invalid");

        assertEquals(expected, pdfManager.readPdf("./pdf/CS2022_L01.pdf"));
    }

    //"Not a PDF"
    @Test
    public void readPdfTest2(){
        PdfManager pdfManager = new PdfManager();

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Not a PDF");

        assertEquals(expected, pdfManager.readPdf("./pdf/RR phantom.png"));
    }


}