package Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {

    @Test
    public void getMd5Test(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("f9c5c5794732f911005968289704cd9c",accountManager.getMd5("dilanka"));
    }

    @Test
    public void isHasRegisteredTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        assertTrue(accountManager.isHasRegistered("dilanka"));
    }

    @Test
    public void isHasRegisteredTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertFalse(accountManager.isHasRegistered("dil"));
    }

    @Test
    public void registerTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("","",""));
    }

    @Test
    public void registerTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("","dilanka","dilanka"));
    }

    @Test
    public void registerTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("dilanka","","dilanka"));
    }

    @Test
    public void registerTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.register("dilanka","dilanka",""));
    }

    @Test
    public void registerTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("User name cannot be contained spaces",accountManager.register("anu radha","dilanka","dilanka"));
    }

    @Test
    public void registerTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Password and Confirm Password mismatch",accountManager.register("dilanka","dilanka","dilankar"));
    }

    @Test
    public void registerTest7(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Password is too short",accountManager.register("dilanka","dil","dil"));
    }

    @Test
    public void registerTest8(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("This username has been used!",accountManager.register("dilanka","dilanka","dilanka"));
    }

    @Test
    public void loginTest1(){//need to signup dilanka
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("success",accountManager.login("dilanka","dilanka"));
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

        assertEquals("Invalid username or password!",accountManager.login("dilanka","dil"));
    }

    @Test
    public void loginTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.login("dil","dilanka"));
    }

    @Test
    public void changeUsernameTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("","",""));
    }

    @Test
    public void changeUsernameTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("","dilanka","dilankar"));
    }

    @Test
    public void changeUsernameTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","","dilankar"));
    }

    @Test
    public void changeUsernameTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changeUsername("dilanka","dilanka",""));
    }

    @Test
    public void changeUsernameTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("User name cannot be contained spaces",accountManager.changeUsername("dilanka","dilanka","dilanka r"));
    }

    @Test
    public void changeUsernameTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("This username has been used!",accountManager.changeUsername("dilanka","dilanka","anuradha"));
    }

    @Test
    public void changeUsernameTest7(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("This username has been used!",accountManager.changeUsername("dilanka","dilanka","anuradha"));
    }

    @Test
    public void changeUsernameTest8(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.changeUsername("dil","dilanka","dilankar"));
    }

    @Test
    public void changeUsernameTest9(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Invalid username or password!",accountManager.changeUsername("dilanka","dil","dilankar"));
    }

    @Test
    public void changePasswordTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("","",""));
    }

    @Test
    public void changePasswordTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("","dilanka","dilankar"));
    }

    @Test
    public void changePasswordTest3(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka",""));
    }

    @Test
    public void changePasswordTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("dilanka","","dilankar"));
    }

    @Test
    public void changePasswordTest5(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Please enter required data",accountManager.changePassword("dilanka","dilanka",""));
    }

    @Test
    public void changePasswordTest6(){
        AccountManager accountManager = AccountManager.getInstance();

        assertEquals("Password is too short",accountManager.changePassword("dilanka","dilanka","dil"));
    }

}