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

    //createIndex
    @Test
    public void createIndexTest1() {
        IndexManager indexManager = IndexManager.getInstance();

        assertEquals("Successfully Indexed", indexManager.createIndex("./pdf/CS2022_L01_Introduction.pdf","./index"));


    }

    @Test
    public void createIndexTest2() {
        IndexManager indexManager = IndexManager.getInstance();

        assertEquals("File or dir not exist", indexManager.createIndex("./pdf/CS2022_Introduction.pdf","./index"));


    }
}