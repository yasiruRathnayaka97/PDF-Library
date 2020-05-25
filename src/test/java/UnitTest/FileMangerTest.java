package UnitTest;

import Models.FileManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileMangerTest {
    FileManager fileManager;

    @Before
    public void setup(){
        fileManager = new FileManager();
    }

    //createDir
    @Test
    public void createDirTest1(){
        assertEquals("Dir created successfully",fileManager.createDir("./pdf/Dilanka/Dilanka"));

        fileManager.clearDir("./pdf/Dilanka");
    }

    @Test
    public void createDirTest2(){
        assertEquals("Dir almost exist",fileManager.createDir("./pdf/Dilanka"));

        fileManager.clearDir("./pdf/Dilanka");
    }

    //clearDir
    @Test
    public void clearDirTest1(){
        assertEquals("deleted",fileManager.clearDir("./pdf/Dilanka"));
    }

    //getAllPDFUnderDir
    @Test
    public void getAllPDFUnderDirTest(){
        List<String> expected = new ArrayList<String>();
        expected.add(".\\pdf\\CS2022_L01_Introduction.pdf");

        assertEquals(expected,fileManager.getAllPDFUnderDir("./pdf"));
    }
}
