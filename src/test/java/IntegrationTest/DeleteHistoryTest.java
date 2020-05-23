package IntegrationTest;

import Models.AccountManager;
import Models.HistoryItem;
import Models.HistoryManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DeleteHistoryTest {
    AccountManager accountManager;
    HistoryManager historyManager;

    @Before
    public void setup(){
        accountManager = AccountManager.getInstance();
        accountManager.register("Dilanka","Dilanka","Dilanka");
        historyManager = HistoryManager.getInstance();
    }

    //deleteOne
    @Test
    public void deleteOneTest(){
        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        ArrayList<HistoryItem> history = historyManager.getHistory();

        assertTrue(historyManager.deleteOne(history.get(0).getId()));

        accountManager.deleteAccount("Dilanka");
    }

    //deleteAll
    @Test
    public void deleteAllTest(){
        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        ArrayList<HistoryItem> history = historyManager.getHistory();

        assertTrue(historyManager.deleteAll());
    }

    @After
    public void teardown(){
        accountManager.deleteAccount("Dilanka");
    }
}
