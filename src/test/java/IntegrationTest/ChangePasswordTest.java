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

    @Test
    public void allEmptyInputs(){
        assertEquals("Please enter required data",accountManager.changePassword("","","",""));
    }

    @Test
    public void emptyUsername(){
        assertEquals("Please enter required data",accountManager.changePassword("","dilanka","Anuradha","Anuradha"));
    }

    @Test
    public void emptyPassword(){
        assertEquals("Please enter required data",accountManager.changePassword("dilanka","","Anuradha","Anuradha"));
    }

    @Test
    public void emptyNewPassword(){
        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka","","Anuradha"));
    }

    @Test
    public void emptyConfPassword(){
        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka","Anuradha",""));
    }

    @Test
    public void shortNewPassword(){
        assertEquals("Password is too short!",accountManager.changePassword("Dilanka","Dilanka","Anu","Anu"));
    }

    @Test
    public void newPasswordConfPasswordMismatch(){
        assertEquals("New password and confirm new password mismatch!",accountManager.changePassword("Dilanka","Dilanka","Dilanka","Anuradha"));
    }

    @Test
    public void invalidUser(){
        assertEquals("Invalid username or password!",accountManager.changePassword("dil","Dilanka","Anuradha","Anuradha"));
    }

    @After
    public void teardown(){
        accountManager.deleteAccount("Dilanka");
    }
}
