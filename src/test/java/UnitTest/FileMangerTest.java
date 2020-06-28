package UnitTest;

import Models.FileManager;
import Models.State;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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



    //getAllPDFUnderDir
    @Test
    public void getAllPDFUnderDirTest(){
        List<String> expected = new ArrayList<String>();
        expected.add(".\\pdf\\CS2022_L01_Introduction.pdf");

        assertEquals(expected,fileManager.getAllPDFUnderDir("./pdf"));
    }

    @Test
    public void writeSubIndexDirInfoTest(){
        String superParentPath="./sample/sampleSub";
        ArrayList<String> pathList=new ArrayList<String>();
        State s=new State();
        int weight=100;
        pathList.add("./sample.pdf");
        s.setSuperParentPath(superParentPath);
        String indexDir="index_"+s.getSuperParentPath();
        assertEquals("index_./sample/sampleSub,./sample/sampleSub,100,./sample.pdf\n",fileManager.writeSubIndexDirInfo(indexDir,superParentPath,weight,pathList));
    }
    @Test
    public void writeIndexDirInfoTest(){
        String parentPath="./sample/sampleSub";
        ArrayList<String> pathList=new ArrayList<String>();
        ArrayList<State> stateList=new ArrayList<State>();
        State s1=new State();
        State s2=new State();
        int weight=100;
        pathList.add("./sample.pdf");
        s1.setPathList(pathList);
        s1.setWeight(weight);
        s1.setSuperParentPath(parentPath);
        String indexDir="index_"+s1.getSuperParentPath();
        s1.setIndexDir(indexDir);
        s2.setPathList(pathList);
        s2.setWeight(weight);
        s2.setSuperParentPath(parentPath);
        s2.setIndexDir(indexDir);
        stateList.add(s1);
        stateList.add(s2);
        assertTrue( fileManager.writeIndexDirInfo(stateList));
    }

    @Test
    public void readIndexDirInfoTest() throws IOException {
        ArrayList<State> list=new ArrayList<State>();
        String parentPath="./sample/sampleSub";
        ArrayList<String> pathList=new ArrayList<String>();
        ArrayList<State> stateList=new ArrayList<State>();
        State s1=new State();
        State s2=new State();
        int weight=100;
        pathList.add("./sample.pdf");
        s1.setPathList(pathList);
        s1.setWeight(weight);
        s1.setSuperParentPath(parentPath);
        String indexDir="index_"+s1.getSuperParentPath();
        s1.setIndexDir(indexDir);
        s2.setPathList(pathList);
        s2.setWeight(weight);
        s2.setSuperParentPath(parentPath);
        s2.setIndexDir(indexDir);
        stateList.add(s1);
        stateList.add(s2);
        assertEquals(stateList.size(),fileManager.readIndexDirInfo(list).size());
        //objects not equal to each other.
    }
    //clearDir
    @Test
    public void clearDirTest1(){
        assertEquals("deleted",fileManager.clearDir("./pdf/Dilanka"));
    }
}
