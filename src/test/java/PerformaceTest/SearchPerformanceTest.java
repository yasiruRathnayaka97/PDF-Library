package PerformaceTest;

import org.junit.Before;
import org.junit.Test;
import Models.SearchManager;
public class SearchPerformanceTest {
    SearchManager searchManager;
    @Before
    public void setup(){
         this.searchManager = SearchManager.getInstance();
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
