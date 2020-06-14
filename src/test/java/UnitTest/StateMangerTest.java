package UnitTest;

import Models.State;
import Models.StateManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateMangerTest {
    StateManager sm;
    @Before
    public void setup(){
        sm=StateManager.getInstance();
        sm.setStateList(new ArrayList<State>());
    }
    @Test
    public void stateListEmpty(){
        String parentPath="./sample";
        ArrayList<String> pathList=new ArrayList<String>();
        int weight=100;
        State res=sm.updateStates(parentPath,pathList,weight);
        assertEquals("./sample",res.getSuperParentPath());
    }
    @Test
    public void stateListUpdate(){
        String parentPath="./sample";
        ArrayList<String> pathList=new ArrayList<String>();
        State s=new State();
        int weight=100;
        pathList.add("./sample/sampleSub/sample.pdf");
        s.setPathList(pathList);
        s.setWeight(weight);
        s.setSuperParentPath(parentPath);
        State res=sm.updateStates(parentPath,pathList,weight);
        assertEquals("./sample",res.getSuperParentPath());
    }
    @Test
    public void stateListNewAddition(){
        String parentPath="./sample/sampleSub";
        ArrayList<String> pathList=new ArrayList<String>();
        State s=new State();
        int weight=100;
        pathList.add("./sample.pdf");
        s.setPathList(pathList);
        s.setWeight(weight);
        s.setSuperParentPath(parentPath);
        State res=sm.updateStates(parentPath,pathList,weight);
        assertEquals("./sample/sampleSub",res.getSuperParentPath());
    }
}
