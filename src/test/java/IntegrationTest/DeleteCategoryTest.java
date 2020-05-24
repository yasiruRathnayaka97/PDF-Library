package IntegrationTest;

import Models.AccountManager;
import Models.FavoriteManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteCategoryTest {
    @Test
    public void success(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("./pdf/CS2022_L01_Introduction.pdf");
        favoriteManager.setKeyword("The");
        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        assertEquals("success",favoriteManager.addFavorite(false,"A"));

        accountManager.deleteAccount("Dilanka");
    }
}
