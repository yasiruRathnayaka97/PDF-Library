package IntegrationTest;

import Models.AccountManager;
import org.junit.Before;
import org.junit.Test;
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

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void allEmptyInputs(){
        assertEquals("Please enter required data",accountManager.register("","",""));
    }

    @Test
    public void emptyUsernameInputs(){
        assertEquals("Please enter required data",accountManager.register("","dilanka","dilanka"));
    }

    @Test
    public void emptyPasswordInput(){
        assertEquals("Please enter required data",accountManager.register("dilanka","","dilanka"));
    }

    @Test
    public void emptyConfPasswordInput(){
        assertEquals("Please enter required data",accountManager.register("dilanka","dilanka",""));
    }

    @Test
    public void usernameWithSpace(){
        assertEquals("User name cannot be contained spaces",accountManager.register("anu radha","dilanka","dilanka"));
    }

    @Test
    public void PasswordConfirmPasswordMismatch(){
        assertEquals("Password and Confirm Password mismatch",accountManager.register("dilanka","dilanka","dilankar"));
    }

    @Test
    public void shortPassword(){
        assertEquals("Password is too short",accountManager.register("dilanka","dil","dil"));
    }

    @Test
    public void usernameIsUsed(){
        accountManager.register("Dilanka","Dilanka", "Dilanka");

        accountManager.register("Anuradha","Anuradha", "Anuradha");

        assertEquals("This username has been used!",accountManager.register("Dilanka","Dilanka","Dilanka"));

        accountManager.deleteAccount("Anuradha");

        accountManager.deleteAccount("Dilanka");
    }
}
