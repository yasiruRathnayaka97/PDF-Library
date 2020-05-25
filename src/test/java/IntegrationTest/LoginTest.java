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
    public void incorrectPassword(){
        assertEquals("Invalid username or password!",accountManager.login("Dilanka","dilanka"));
    }

    @Test
    public void allEmptyInputs(){
        assertEquals("Please enter required data",accountManager.login("",""));
    }

    @Test
    public void emptyPassword(){
        assertEquals("Please enter required data",accountManager.login("dilanka",""));
    }

    @Test
    public void emptyUsername(){
        assertEquals("Please enter required data",accountManager.login("","dilanka"));
    }

    @Test
    public void invalidUsername(){
        assertEquals("Invalid username or password!",accountManager.login("dil","dilanka"));
    }

    @After
    public void teardown(){
        accountManager.deleteAccount("Dilanka");
    }
}
