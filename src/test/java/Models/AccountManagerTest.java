package Models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

    //isHasRegistered === ? TODO
    @Test
    public void isHasRegisteredTest1(){
        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertTrue(accountManager.isHasRegistered("Dilanka"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test //=== ? TODO
    public void isHasRegisteredTest2(){
        assertFalse(accountManager.isHasRegistered("dil"));
    }

    @Test
    public void registerTest2(){
        assertEquals("Please enter required data",accountManager.register("","",""));
    }

    @Test
    public void registerTest3(){
        assertEquals("Please enter required data",accountManager.register("","dilanka","dilanka"));
    }

    @Test
    public void registerTest4(){
        assertEquals("Please enter required data",accountManager.register("dilanka","","dilanka"));
    }

    @Test
    public void registerTest5(){
        assertEquals("Please enter required data",accountManager.register("dilanka","dilanka",""));
    }

    @Test
    public void registerTest6(){
        assertEquals("User name cannot be contained spaces",accountManager.register("anu radha","dilanka","dilanka"));
    }

    @Test
    public void registerTest7(){
        assertEquals("Password and Confirm Password mismatch",accountManager.register("dilanka","dilanka","dilankar"));
    }

    @Test
    public void registerTest8(){
        assertEquals("Password is too short",accountManager.register("dilanka","dil","dil"));
    }

    //login
    @Test
    public void loginTest2(){
        assertEquals("Please enter required data",accountManager.login("",""));
    }

    @Test
    public void loginTest3(){
        assertEquals("Please enter required data",accountManager.login("dilanka",""));
    }

    @Test
    public void loginTest4(){
        assertEquals("Please enter required data",accountManager.login("","dilanka"));
    }

    @Test
    public void loginTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.login("dil","dilanka"));
    }

    //changeUsername
    @Test
    public void changeUsernameTest2(){
        assertEquals("Please enter required data",accountManager.changeUsername("","",""));
    }

    @Test
    public void changeUsernameTest3(){
        assertEquals("Please enter required data",accountManager.changeUsername("","dilanka","dilankar"));
    }

    @Test
    public void changeUsernameTest4(){
        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","","dilankar"));
    }

    @Test
    public void changeUsernameTest5(){
        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","dilanka",""));
    }

    @Test
    public void changeUsernameTest6(){
        assertEquals("User name cannot be contained spaces",accountManager.changeUsername("dilanka","dilanka","dilanka r"));
    }

    @Test
    public void changeUsernameTest9(){
        assertEquals("Invalid username or password!",accountManager.changeUsername("dil","dilanka","dilankar"));
    }

    //changePassword
    @Test
    public void changePasswordTest2(){
        assertEquals("Please enter required data",accountManager.changePassword("","","",""));
    }

    @Test
    public void changePasswordTest3(){
        assertEquals("Please enter required data",accountManager.changePassword("","dilanka","Anuradha","Anuradha"));
    }

    @Test
    public void changePasswordTest4(){
        assertEquals("Please enter required data",accountManager.changePassword("dilanka","","Anuradha","Anuradha"));
    }

    @Test
    public void changePasswordTest5(){
        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka","","Anuradha"));
    }

    @Test
    public void changePasswordTest6(){
        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka","Anuradha",""));
    }

    @Test
    public void changePasswordTest7(){
        assertEquals("Password is too short!",accountManager.changePassword("Dilanka","Dilanka","Anu","Anu"));
    }

    @Test
    public void changePasswordTest8(){
        assertEquals("New password and confirm new password mismatch!",accountManager.changePassword("Dilanka","Dilanka","Dilanka","Anuradha"));
    }

    @Test
    public void changePasswordTest9(){
        assertEquals("Invalid username or password!",accountManager.changePassword("Dilanka","Dilanka","Anuradha","Anuradha"));
    }

    //checkLoggingCorrect
    @Test
    public void checkLoggingCorrectTest1(){
        assertFalse(accountManager.checkLoggingCorrect("dilanka","dilanka"));
    }

    @Test //TODO ==> integration test
    public void checkLoggingCorrectTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertTrue(accountManager.checkLoggingCorrect("Dilanka","f2a0bcf7994da4c6ad5c6bf33c612506"));

        accountManager.deleteAccount("Dilanka");
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
//If error is appeared, delete db