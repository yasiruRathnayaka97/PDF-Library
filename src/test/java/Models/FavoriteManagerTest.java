package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FavoriteManagerTest {
    //addFavoriteTest
    //test on add new category success
    @Test
    public void addFavoriteTest1(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        assertEquals("success",favoriteManager.addFavorite(true,"Crime"));

        accountManager.deleteAccount("Dilanka");
    }

    //Test on empty categoryName
    @Test
    public void addFavoriteTest2(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        assertEquals("Enter new category name!",favoriteManager.addFavorite(true,""));

        accountManager.deleteAccount("Dilanka");
    }

    //Test on add to existing category
    @Test
    public void addFavoriteTest3(){ //TODO metana hadanna puluwanda balanna nettan delete karanna
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("DilankaAdmin","DilankaAdmin","DilankaAdmin");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"Crime");

        assertEquals("success",favoriteManager.addFavorite(false,"Crime"));

        accountManager.deleteAccount("DilankaAdmin");
    }

    //Test on create new category with existing name
    @Test
    public void addFavoriteTest4(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"Crime");

        assertEquals("Category is already existed!",favoriteManager.addFavorite(true,"Crime"));

        accountManager.deleteAccount("Dilanka");
    }

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

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

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

    //getCategoryFromDB
    @Test
    public void getCategoryFromDBTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        favoriteManager.addFavorite(true,"B");

        favoriteManager.addFavorite(true,"C");

        favoriteManager.addFavorite(true,"D");

        assertEquals("success",favoriteManager.getCategoryFromDB());

        accountManager.deleteAccount("Dilanka");
    }

    //deleteFav
    @Test
    public void deleteFavTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        ObservableList<FavoriteItem> favorites = FXCollections.observableArrayList();

        favorites = favoriteManager.getFavorites("A");

        assertTrue(favoriteManager.deleteFav(favorites.get(0),"A"));

        accountManager.deleteAccount("Dilanka");
    }

    //clearFav
    @Test
    public void clearFavTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        favoriteManager.addFavorite(false,"A");

        favoriteManager.addFavorite(false,"A");

        favoriteManager.addFavorite(false,"A");

        assertTrue(favoriteManager.clearFav("A"));

        accountManager.deleteAccount("Dilanka");
    }

    //deleteCategory
    @Test
    public void deleteCategoryTest(){
        AccountManager accountManager = AccountManager.getInstance();

        accountManager.register("Dilanka","Dilanka","Dilanka");

        FavoriteManager favoriteManager = FavoriteManager.getInstance();

        favoriteManager.setPath("C:\\Users\\DilankaRathnasiri\\Desktop\\Godfather 01 - The Godfather ( PDFDrive.com ).pdf");

        favoriteManager.setKeyword("Godfather");

        favoriteManager.setSearchType("content");

        favoriteManager.addFavorite(true,"A");

        assertTrue(favoriteManager.deleteCategory("A"));

        accountManager.deleteAccount("Dilanka");
    }
}