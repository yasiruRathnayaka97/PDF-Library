package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class CategoryItem {
    private final String name;
    private ObservableList<FavoriteItem> favorites;

    public CategoryItem(String name) {
        this.name = name;
        favorites = FXCollections.observableArrayList();
    }

    public void addFavourite(FavoriteItem favoriteItem){
        favorites.add(favoriteItem);
    }

    public void deleteFavourite(FavoriteItem favoriteItem){
        favorites.remove(favoriteItem);
    }

    public void clearFavorites(){
        favorites.clear();
    }

    public String getName() {
        return name;
    }

    public ObservableList<FavoriteItem> getFavorites() {
        return favorites;
    }
}
