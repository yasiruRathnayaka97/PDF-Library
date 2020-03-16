
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IndexAndSearchManagerTest {
    IndexManager im;
    SearchManager sm;
    public IndexAndSearchManagerTest(){
        im=new IndexManager();
        sm=new SearchManager(im);
    }
    @Before
    public void createIndexTest() {
        String filePath = "C:/Users/YJR/Desktop/snmp.pdf";
        String dirPath="index";
        assertEquals("Successfully Indexed", im.createIndex(filePath,dirPath));


    }
    @Test
    public  void searchIndexTest(){
        String dirPath="index";
        System.out.println(sm.search(dirPath,"content","snmp supported software foundation"));

    }

}


