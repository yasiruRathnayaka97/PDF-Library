package IntegrationTest;

import Models.AccountManager;
import Models.IndexManager;
import Models.SearchManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    @Test
    public void success(){
        SearchManager searchManager = SearchManager.getInstance();

        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        IndexManager indexManager = IndexManager.getInstance();

        indexManager.setDirPath("C:\\Users\\DilankaRathnasiri\\Documents\\sample");

        assertEquals("success",searchManager.setSearchResult("The","content"));

        accountManager.deleteAccount("Dilanka");
    }
}
