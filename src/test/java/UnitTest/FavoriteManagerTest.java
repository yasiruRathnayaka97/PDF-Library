package UnitTest;

import Models.AccountManager;
import Models.FavoriteManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FavoriteManagerTest {
    //getCategoryNames
    @Test
    public void getCategoryNamesTest(){
        ObservableList<String> expected = FXCollections.observableArrayList();

        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");

        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("./pdf/CS2022_L01_Introduction.pdf");

        favoriteManager.setKeyword("The");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        favoriteManager.addFavorite(true,"B");

        favoriteManager.addFavorite(true,"C");

        favoriteManager.addFavorite(true,"D");

        ObservableList<String> actual = FXCollections.observableArrayList();

        actual = favoriteManager.getCategoryNames();

        boolean equalLists =true;

        if(expected.size()!=actual.size()){
            equalLists = false;
        }

        for (int i=0; i<expected.size();i++){
            if (actual.indexOf(expected.get(i))==-1){
                equalLists = false;
            }

            if (expected.indexOf(actual.get(i))==-1){
                equalLists = false;
            }
        }

        assertTrue(equalLists);

        accountManager.deleteAccount("Dilanka");
    }
}
