package IntegrationTest;

import Models.AccountManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

public class RegisterTest {
    AccountManager accountManager;

    @Before
    public void setup(){
        accountManager = AccountManager.getInstance();
    }

    @Test
    public void success(){
        assertEquals("success",accountManager.register("Dilanka","Dilanka", "Dilanka"));
    }

    @Test
    public void usernameIsUsed(){
        accountManager.register("Dilanka","Dilanka", "Dilanka");

        accountManager.register("Anuradha","Anuradha", "Anuradha");

        assertEquals("This username has been used!",accountManager.register("Dilanka","Dilanka","Dilanka"));

        accountManager.deleteAccount("Anuradha");
    }

    @After
    public void teardown(){
        accountManager.deleteAccount("Dilanka");
    }


}
