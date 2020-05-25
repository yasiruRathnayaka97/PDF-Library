package IntegrationTest;

import Models.AccountManager;
import Models.HistoryManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddHistoryTest {
    //success
    @Test
    public void success(){
        AccountManager accountManager = AccountManager.getInstance();
        accountManager.register("Dilanka","Dilanka","Dilanka");

        HistoryManager historyManager = HistoryManager.getInstance();

        assertTrue(historyManager.addHistory("The","content","./pdf"));

        accountManager.deleteAccount("Dilanka");
    }
}
