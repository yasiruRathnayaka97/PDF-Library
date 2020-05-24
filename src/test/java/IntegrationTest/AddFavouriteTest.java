package IntegrationTest;

import Models.AccountManager;
import Models.FavoriteManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddFavouriteTest {
    AccountManager accountManager;
    FavoriteManager favoriteManager;

    @Before
    public void startup(){
        accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        favoriteManager = FavoriteManager.getInstance();
    }

    @Test
    public void addToNewCategorySuccess(){
        favoriteManager.setPath("./CS2022_L01_Introduction.pdf");

        favoriteManager.setKeyword("The");

        favoriteManager.setSearchType("content");

        assertEquals("success",favoriteManager.addFavorite(true,"A"));
    }

    @Test
    public void addExistingCategorySuccess(){
        favoriteManager.setPath("./pdf/CS2022_L01_Introduction.pdf");

        favoriteManager.setKeyword("The");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        assertEquals("success",favoriteManager.addFavorite(false,"A"));
    }

    @After
    public void teardown(){
        favoriteManager.deleteCategory("A");

        accountManager.deleteAccount("Dilanka");
    }
}
