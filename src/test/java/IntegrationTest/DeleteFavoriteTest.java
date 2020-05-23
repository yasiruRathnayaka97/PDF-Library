package IntegrationTest;

import Models.AccountManager;
import Models.FavoriteItem;
import Models.FavoriteManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DeleteFavoriteTest {
    AccountManager accountManager;
    FavoriteManager favoriteManager;

    @Before
    public void setup(){
        accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        favoriteManager = FavoriteManager.getInstance();
    }

    @Test
    public void successfullyDeleteOneItem(){
        favoriteManager.setPath("./pdf/CS2022_L01_Introduction.pdf");

        favoriteManager.setKeyword("The");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        ObservableList<FavoriteItem> favorites = FXCollections.observableArrayList();

        favorites = favoriteManager.getFavorites("A");

        assertTrue(favoriteManager.deleteFav(favorites.get(0),"A"));
    }

    //clearFav
    @Test
    public void successfullyClearOneCategory(){
        favoriteManager.setPath("./pdf/CS2022_L01_Introduction.pdf");

        favoriteManager.setKeyword("The");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        favoriteManager.addFavorite(false,"A");

        favoriteManager.addFavorite(false,"A");

        favoriteManager.addFavorite(false,"A");

        assertTrue(favoriteManager.clearFav("A"));
    }

    @After
    public void teardown(){
        favoriteManager.deleteCategory("A");
        accountManager.deleteAccount("Dilanka");
    }
}
