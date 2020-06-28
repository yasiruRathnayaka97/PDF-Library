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
        sm.updateStates(parentPath,pathList);
        String  res=sm.getCurrentState().getSuperParentPath();
        assertEquals("./sample",res);
    }
    @Test
    public void stateListUpdate(){
        String parentPath="./sample";
        ArrayList<String> pathList=new ArrayList<String>();
        State s=new State();
        pathList.add("./sample/sampleSub/sample.pdf");
        s.setPathList(pathList);
        s.setSuperParentPath(parentPath);
        sm.updateStates(parentPath,pathList);
        String res=sm.getCurrentState().getSuperParentPath();
        assertEquals("./sample",res);
    }
    @Test
    public void stateListNewAddition(){
        String parentPath="./sample/sampleSub";
        ArrayList<String> pathList=new ArrayList<String>();
        State s=new State();
        pathList.add("./sample.pdf");
        s.setPathList(pathList);
        s.setSuperParentPath(parentPath);
        sm.updateStates(parentPath,pathList);
        String res=sm.getCurrentState().getSuperParentPath();
        assertEquals("./sample/sampleSub",res);
    }
}
