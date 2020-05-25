package UnitTest;

import Models.PdfManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PDFManagerTest {
    PdfManager pdfManager;

    @Before
    public void setup(){
        pdfManager = new PdfManager();
    }

    //openPdf -- file exists
    @Test
    public void openPdfTest1() {
        assertEquals("Done",pdfManager.openPdf("./pdf/CS2022_L01_Introduction.pdf"));
    }

    //openPdf -- file not exists
    @Test
    public void openPdfTest2() {
        assertEquals("File is not exists!",pdfManager.openPdf("./pdf/CS2022_L01.pdf"));
    }

    //readPdf -- "File path is invalid"
    @Test
    public void readPdfTest1(){
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("File path is invalid");

        assertEquals(expected, pdfManager.readPdf("./pdf/CS2022_L01.pdf"));
    }

    //"Not a PDF"
    @Test
    public void readPdfTest2(){
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Not a PDF");

        assertEquals(expected, pdfManager.readPdf("./pdf/RR phantom.png"));
    }
}
