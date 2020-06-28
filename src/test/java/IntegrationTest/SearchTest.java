package IntegrationTest;

import Models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    StateManager sm;
    @Before
    public void setup(){
        sm= StateManager.getInstance();
        sm.setStateList(new ArrayList<State>());
        State s=new State();
        s.setWeight(0);
        sm.setCurrentState(s);
        sm.getCurrentState().setIndexDir("./index_large");

    }

    @Test
    public void success(){
        SearchManager searchManager = SearchManager.getInstance();

        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        IndexManager indexManager = IndexManager.getInstance();

        indexManager.setDirPath("./pdf");

       assertEquals("success",searchManager.setSearchResult("The","content"));

        accountManager.deleteAccount("Dilanka");
    }
}
