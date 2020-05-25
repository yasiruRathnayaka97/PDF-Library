package IntegrationTest;

import Models.AccountManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeUsernameTest {
    AccountManager accountManager;

    @Before
    public void startup(){
        accountManager = AccountManager.getInstance();
    }

    @Test
    public void success(){
        accountManager.register("Dilanka","Dilanka","Dilanka");
        assertEquals("success",accountManager.changeUsername("Dilanka","Dilanka","Anuradha"));
        accountManager.deleteAccount("Anuradha");
    }

    @Test
    public void allEmptyInputs(){
        assertEquals("Please enter required data",accountManager.changeUsername("","",""));
    }

    @Test
    public void emptyUsername(){
        assertEquals("Please enter required data",accountManager.changeUsername("","dilanka","dilankar"));
    }

    @Test
    public void emptyPassword(){
        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","","dilankar"));
    }

    @Test
    public void emptyNewUsername(){
        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","dilanka",""));
    }

    @Test
    public void newUsernameWithSpace(){
        assertEquals("User name cannot be contained spaces",accountManager.changeUsername("dilanka","dilanka","dilanka r"));
    }

    @Test
    public void InvalidUsername(){
        assertEquals("Invalid username or password!",accountManager.changeUsername("dil","dilanka","dilankar"));
    }

    @Test
    public void usernameIsUsed(){
        accountManager.register("Dilanka","Dilanka","Dilanka");

        accountManager.register("Anuradha","Dilanka","Dilanka");

        assertEquals("This username has been used!",accountManager.changeUsername("Dilanka","Dilanka","Anuradha"));

        accountManager.deleteAccount("Anuradha");

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void incorrectPassword(){
        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("Invalid username or password!",accountManager.changeUsername("Dilanka","Dil","dilankar"));

        accountManager.deleteAccount("Dilanka");
    }
}
