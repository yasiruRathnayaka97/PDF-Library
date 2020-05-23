package IntegrationTest;

import Models.AccountManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ChangePasswordTest {
    AccountManager accountManager;

    @Before
    public void setup(){
        accountManager = AccountManager.getInstance();
        accountManager.register("Dilanka","Dilanka","Dilanka");
    }

    @Test
    public void success(){
        assertEquals("success",accountManager.changePassword("Dilanka","Dilanka","Anuradha","Anuradha"));
    }

    @Test
    public void incorrectPassword(){
        assertEquals("Invalid username or password!",accountManager.changePassword("Dilanka","Anuradha","Dilanka","Dilanka"));
    }

    @After
    public void teardown(){
        accountManager.deleteAccount("Dilanka");
    }
}
