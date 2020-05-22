package Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchManagerTest {
    //setSearchResult
    @Test
    public void setSearchResultTest(){
        SearchManager searchManager = SearchManager.getInstance();

        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        IndexManager indexManager = IndexManager.getInstance();

        indexManager.setDirPath("C:\\Users\\DilankaRathnasiri\\Documents\\sample");

        assertEquals("success",searchManager.setSearchResult("The","content"));

        accountManager.deleteAccount("Dilanka");
    }

    //

}