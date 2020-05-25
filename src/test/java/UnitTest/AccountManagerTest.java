package UnitTest;

import Models.AccountManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountManagerTest {
    AccountManager accountManager;

    @Before
    public void startup(){
        accountManager = AccountManager.getInstance();
    }

    //Md5
    @Test
    public void getMd5Test(){
        assertEquals("f9c5c5794732f911005968289704cd9c",accountManager.getMd5("dilanka"));
    }

    //readLastLogged
    @Test
    public void readLastLoggedTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertTrue(accountManager.readLastLogged());

        accountManager.deleteAccount("Dilanka");
    }
}
