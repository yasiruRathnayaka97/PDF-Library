package Models;

import java.util.HashMap;

public class CategoryItem {
    private final String name;
    private HashMap<String,FavoriteItem> favorites;

    public CategoryItem(String name) {
        this.name = name;
        favorites = new HashMap<String, FavoriteItem>();
    }

    public void addFavourite(String id, FavoriteItem favoriteItem){
        favorites.put(id, favoriteItem);
    }

    public void deleteFavourite(String id){
        favorites.remove(id);
    }

    public void clearFavorites(){
        favorites.clear();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, FavoriteItem> getFavorites() {
        return favorites;
    }
}
