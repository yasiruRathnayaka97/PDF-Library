package Models;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountManagerTest {

    //Md5
    @Test
    public void getMd5Test(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("f9c5c5794732f911005968289704cd9c",accountManager.getMd5("dilanka"));
    }

    //isHasRegistered
    @Test
    public void isHasRegisteredTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertTrue(accountManager.isHasRegistered("Dilanka"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void isHasRegisteredTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertFalse(accountManager.isHasRegistered("dil"));
    }

    //register
    @Test
    public void registerTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("success",accountManager.register("Dilanka","Dilanka", "Dilanka"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void registerTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("","",""));
    }

    @Test
    public void registerTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("","dilanka","dilanka"));
    }

    @Test
    public void registerTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("dilanka","","dilanka"));
    }

    @Test
    public void registerTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("dilanka","dilanka",""));
    }

    @Test
    public void registerTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("User name cannot be contained spaces",accountManager.register("anu radha","dilanka","dilanka"));
    }

    @Test
    public void registerTest7(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Password and Confirm Password mismatch",accountManager.register("dilanka","dilanka","dilankar"));
    }

    @Test
    public void registerTest8(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Password is too short",accountManager.register("dilanka","dil","dil"));
    }

    @Test
    public void registerTest9(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka", "Dilanka");

        accountManager.register("Anuradha","Anuradha", "Anuradha");

        assertEquals("This username has been used!",accountManager.register("Dilanka","Dilanka","Dilanka"));

        accountManager.deleteAccount("Dilanka");

        accountManager.deleteAccount("Anuradha");
    }

    //login
    @Test
    public void loginTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("success",accountManager.login("Dilanka","Dilanka"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void loginTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.login("",""));
    }

    @Test
    public void loginTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.login("dilanka",""));
    }

    @Test
    public void loginTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.login("","dilanka"));
    }

    @Test
    public void loginTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("Invalid username or password!",accountManager.login("Dilanka","dilanka"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void loginTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.login("dil","dilanka"));
    }

    //changeUsername
    @Test
    public void changeUsernameTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("success",accountManager.changeUsername("Dilanka","Dilanka","Anuradha"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void changeUsernameTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("","",""));
    }

    @Test
    public void changeUsernameTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("","dilanka","dilankar"));
    }

    @Test
    public void changeUsernameTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","","dilankar"));
    }

    @Test
    public void changeUsernameTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","dilanka",""));
    }

    @Test
    public void changeUsernameTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("User name cannot be contained spaces",accountManager.changeUsername("dilanka","dilanka","dilanka r"));
    }

    @Test
    public void changeUsernameTest7(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        accountManager.register("Anuradha","Dilanka","Dilanka");

        assertEquals("This username has been used!",accountManager.changeUsername("Dilanka","Dilanka","Anuradha"));

        accountManager.deleteAccount("Dilanka");

        accountManager.deleteAccount("Anuradha");
    }

    @Test
    public void changeUsernameTest9(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.changeUsername("dil","dilanka","dilankar"));
    }

    @Test
    public void changeUsernameTest10(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("Invalid username or password!",accountManager.changeUsername("Dilanka","Dil","dilankar"));

        accountManager.deleteAccount("Dilanka");
    }

    //changePassword
    @Test
    public void changePasswordTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("success",accountManager.changePassword("Dilanka","Dilanka","Anuradha","Anuradha"));

        accountManager.deleteAccount("Dilanka");
    }

    @Test
    public void changePasswordTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("","","",""));
    }

    @Test
    public void changePasswordTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("","dilanka","Anuradha","Anuradha"));
    }

    @Test
    public void changePasswordTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("dilanka","","Anuradha","Anuradha"));
    }

    @Test
    public void changePasswordTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka","","Anuradha"));
    }

    @Test
    public void changePasswordTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka","Anuradha",""));
    }

    @Test
    public void changePasswordTest7(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Password is too short!",accountManager.changePassword("Dilanka","Dilanka","Anu","Anu"));
    }

    @Test
    public void changePasswordTest8(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("New password and confirm new password mismatch!",accountManager.changePassword("Dilanka","Dilanka","Dilanka","Anuradha"));
    }

    @Test
    public void changePasswordTest9(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.changePassword("Dilanka","Dilanka","Anuradha","Anuradha"));
    }

    @Test
    public void changePasswordTest10(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("Invalid username or password!",accountManager.changePassword("Dilanka","Anuradha","Dilanka","Dilanka"));

        accountManager.deleteAccount("Dilanka");
    }

    //checkLoggingCorrect
    @Test
    public void checkLoggingCorrectTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        assertFalse(accountManager.checkLoggingCorrect("dilanka","dilanka"));
    }

    @Test
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