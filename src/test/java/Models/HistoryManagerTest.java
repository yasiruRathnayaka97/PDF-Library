package Models;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HistoryManagerTest {
    //addHistory
    //test on success
    @Test
    public void addHistoryTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        HistoryManager historyManager = HistoryManager.getInstance();

        assertTrue(historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri"));

        accountManager.deleteAccount("Dilanka");
    }

    //deleteOne
    @Test
    public void deleteOneTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        HistoryManager historyManager = HistoryManager.getInstance();

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        ArrayList<HistoryItem> history = historyManager.getHistory();

        assertTrue(historyManager.deleteOne(history.get(0).getId()));

        accountManager.deleteAccount("Dilanka");
    }

    //deleteAll
    @Test
    public void deleteAllTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        HistoryManager historyManager = HistoryManager.getInstance();

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        historyManager.addHistory("The","content","C:\\Users\\DilankaRathnasiri");

        ArrayList<HistoryItem> history = historyManager.getHistory();

        assertTrue(historyManager.deleteAll());

        accountManager.deleteAccount("Dilanka");
    }

}