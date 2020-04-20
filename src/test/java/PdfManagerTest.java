import Models.PdfManager;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PdfManagerTest {
    public PdfManager pm;

    public PdfManagerTest() {
        this.pm = new PdfManager();
    }

//    @Before
//    public  void start() {
//        System.out.println("Test Begin");
//    }

    @Test
    public void pdfReaderTest() {
        String path1 = "C:/Users/YJR/Desktop/SamplePdf.pdf";
        String path2 = "C:/Users/YJR/Desktop/error.pdf";
        String path3 = "C:/Users/YJR/Desktop/SamplePdf.txt";
        assertNotNull(pm.readPdf(path1));
        assertEquals("File path is invalid", pm.readPdf(path2));
        assertEquals("Not AppController pdf", pm.readPdf(path3));
    }

    @Test
    public void pdfOpenTest() {
        String path1 = "C:/Users/YJR/Desktop/SamplePdf.pdf";
        assertEquals("Done", pm.openPdf(path1));
    }


//   @After
//    public void end(){
//       System.out.println("Test End");
//   }

}