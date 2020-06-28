package PerformaceTest;

import Models.State;
import Models.StateManager;
import org.junit.Before;
import org.junit.Test;
import Models.SearchManager;

import java.util.ArrayList;

public class SearchPerformanceTest {
    SearchManager searchManager;
    StateManager sm;
    @Before
    public void setup(){
         this.searchManager = SearchManager.getInstance();
        sm= StateManager.getInstance();
        sm.setStateList(new ArrayList<State>());
        State s=new State();
        s.setWeight(0);
        sm.setCurrentState(s);
        sm.getCurrentState().setIndexDir("./index_large");
    }
    @Test
    public void fewDocumentIndexSet(){
        //few document index set
        long  startTime= System.currentTimeMillis();
        this.searchManager.search("content","The");
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for few document index set : "+execTime);


    }
    @Test
    public void continuesSearchFewDocumentIndexSet(){
        //few document index set search without change the index file directory.
        long  startTime= System.currentTimeMillis();
        this.searchManager.search("content","a");
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for few document index set without change dir : "+execTime);
    }
    @Test
    public void manyDocumentIndexSet(){
        //many document index set
        long  startTime= System.currentTimeMillis();
        this.searchManager.search("content","The");
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for many document index set : "+execTime);
    }
    @Test
    public void continuesSearchManyDocumentIndexSet(){
        //many document index search without change the index file directory.
        long  startTime= System.currentTimeMillis();
        this.searchManager.search("content","a");
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for many document index set without change dir: "+execTime);
    }
}
