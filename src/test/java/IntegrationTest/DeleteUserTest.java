package IntegrationTest;

import Models.AccountManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DeleteUserTest {
    @Test
    public void success(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        assertEquals("success",accountManager.deleteAccount("Dilanka"));
    }
}
