import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class IndexManagerTest {
  IndexManager im;
    public IndexManagerTest(){
        im=new IndexManager();
    }
    @Test
    public void createIndexTest() {
        String filePath = "C:/Users/YJR/Desktop/SamplePdf.pdf";
        String dirPath="index";
        assertEquals("Successfully Indexed", im.createIndex(filePath,dirPath,"Favourites"));
    }

}
