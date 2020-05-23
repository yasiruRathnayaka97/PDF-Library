package IntegrationTest;

import Models.AccountManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    AccountManager accountManager;

    @Before
    public void setup(){
        accountManager = AccountManager.getInstance();
        accountManager.register("Dilanka","Dilanka","Dilanka");
    }

    @Test
    public void success(){
        assertEquals("success",accountManager.login("Dilanka","Dilanka"));
    }

    @Test
    public void userNotRegistered(){
        assertEquals("Invalid username or password!",accountManager.login("Dilanka","dilanka"));
    }

    @After
    public void teardown(){
        accountManager.deleteAccount("Dilanka");
    }
}
