package Models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileManagerTest {
    //saveFile //TODO metana balanna
    /*@Test
    public void saveFileTest(){
        FileManager fileManager = new FileManager();

        AccountManager accountManager = AccountManager.getInstance();
        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertTrue(fileManager.saveFile("./pdf/Dilanka"));

        accountManager.deleteAccount("Dilanka");
    }*/

    //createDir
    @Test
    public void createDirTest1(){
        FileManager fileManager = new FileManager();

        assertEquals("Dir created successfully",fileManager.createDir("./pdf/Dilanka/Dilanka"));

        fileManager.clearDir("./pdf/Dilanka");
    }

    @Test
    public void createDirTest2(){
        FileManager fileManager = new FileManager();

        assertEquals("Dir almost exist",fileManager.createDir("./pdf/Dilanka"));

        fileManager.clearDir("./pdf/Dilanka");
    }

    //clearDir
    @Test
    public void clearDirTest1(){
        FileManager fileManager = new FileManager();

        assertEquals("deleted",fileManager.clearDir("./pdf/Dilanka"));
    }

    //getAllPDFUnderDir
    @Test
    public void getAllPDFUnderDirTest(){
        FileManager fileManager = new FileManager();

        List<String> expected = new ArrayList<String>();
        expected.add(".\\pdf\\CS2022_L01_Introduction.pdf");

        assertEquals(expected,fileManager.getAllPDFUnderDir("./pdf"));
    }

}