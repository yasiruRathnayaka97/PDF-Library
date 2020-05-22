package Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class IndexManagerTest {
    //setDirPath
    @Test
    public void setDirPathTest(){
        IndexManager indexManager = IndexManager.getInstance();

        assertEquals("Paths updated",indexManager.setDirPath("./pdf"));
    }

    //indexDirectory
    @Test
    public void indexDirectoryTest(){
        IndexManager indexManager = IndexManager.getInstance();

        indexManager.setDirPath("./pdf");

        assertEquals("Successfully indexed directory",indexManager.indexDirectory());
    }
}