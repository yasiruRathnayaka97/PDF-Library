import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.assertNotNull;

public class AccountManagerTest {

    AccountManager am;
    public AccountManagerTest(){
        this.am=new AccountManager();
    }
    @Test
    public void InsertRetrieveTest(){
        HashMap<String,String> AccDet=new HashMap();
        AccDet.put("userName","sampleUserName");
        AccDet.put("password","samplePassword");
        am.insert(AccDet,"Account");
        assertNotNull(am.retrieve("userName","sampleUserName","Account"));


    }

}
