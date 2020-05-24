package UnitTest;

import Models.IndexManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndexManagerTest {
    IndexManager indexManager;

    @Before
    public void setup(){
        indexManager = IndexManager.getInstance();
    }

    //setDirPath
    @Test
    public void setDirPathTest(){
        assertEquals("Paths updated",indexManager.setDirPath("./pdf"));
    }

    //indexDirectory
    @Test
    public void indexDirectoryTest(){
        indexManager.setDirPath("./pdf");

        assertEquals("Successfully indexed directory",indexManager.indexDirectory());
    }

    //createIndex -- success
    @Test
    public void createIndexTest1() {
        assertEquals("Successfully Indexed", indexManager.createIndex("./pdf/CS2022_L01_Introduction.pdf","./index"));
    }

    //createIndex -- File or dir not exist
    @Test
    public void createIndexTest2() {
        assertEquals("File or dir not exist", indexManager.createIndex("./pdf/CS2022_Introduction.pdf","./index"));
    }
}
