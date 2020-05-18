package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

public class FavoriteManager {

    private static FavoriteManager instance;

    private FavoriteManager(){
        dbManager = DBManager.getInstance();
        accountManager = AccountManager.getInstance();
        categories = new HashMap<String, CategoryItem>();
        getCategoryFromDB();
    };

    public static FavoriteManager getInstance(){
        if (instance == null)
            instance = new FavoriteManager();
        return instance;
    }

    private DBManager dbManager;
    private AccountManager accountManager;
    private HashMap<String,CategoryItem> categories;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet resultSet;
    private String path;
    private String keyword;
    private String searchType;

    public void setPath(String path) {
        System.out.println(path);
        this.path = path;
    }

    public void setKeyword(String keyword) {
        System.out.println(keyword);
        this.keyword = keyword;
    }

    public void setSearchType(String searchType) {
        System.out.println(searchType);
        this.searchType = searchType;
    }

    public String getPath() {
        return path;
    }

    public void getCategoryFromDB(){
        try {
            conn=dbManager.connect();
            stmt = conn.prepareStatement("SELECT id,name FROM Category WHERE username=?");
            stmt.setString(1,accountManager.getUsername().getValue());
            resultSet = stmt.executeQuery();
            while(resultSet.next()){
                categories.put(resultSet.getString("id"),new CategoryItem(resultSet.getString("name")));
            };
            stmt.close();

            for (String id : categories.keySet()) {
                stmt = conn.prepareStatement("SELECT id,path,keyword,searchType FROM favorite WHERE username=? AND category=?");
                stmt.setString(1,accountManager.getUsername().getValue());
                stmt.setString(2,id);
                resultSet = stmt.executeQuery();
                while(resultSet.next()){
                    categories.get(id).addFavourite(new FavoriteItem(resultSet.getString("id"), resultSet.getString("path"), resultSet.getString("keyword"), resultSet.getString("searchType")));
                    System.out.println("category favorite size: "+categories.get(id).getFavorites().size());
                }
            }
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getCategoryNames(){
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for(String id:categories.keySet()){
            categoryNames.add(categories.get(id).getName());
        }
        return categoryNames;
    }

    public String addFavorite(boolean newCategory,String categoryName){
        try {
            conn=dbManager.connect();

            if(newCategory){
                if(categoryName.isEmpty()){
                    return "Enter new category name!";
                }

                if (getCategoryID(categoryName)!=null){
                    return "Category is already existed!";
                }

                String categoryID = LocalDateTime.now().toString();
                String favoriteID = categoryID;

                stmt = conn.prepareStatement("INSERT INTO category(id,name,username) VALUES (?,?,?)");
                stmt.setString(1,categoryID);
                stmt.setString(2,categoryName);
                stmt.setString(3,accountManager.getUsername().getValue());
                stmt.executeUpdate();
                stmt.close();

                stmt = conn.prepareStatement("INSERT INTO favorite(id,path,keyword,searchType,category,username) VALUES (?,?,?,?,?,?)");
                stmt.setString(1,favoriteID);
                stmt.setString(2,path);
                stmt.setString(3,keyword);
                stmt.setString(4,searchType);
                stmt.setString(5,categoryID);
                stmt.setString(6,accountManager.getUsername().getValue());
                stmt.executeUpdate();
                stmt.close();

                CategoryItem categoryItem = new CategoryItem(categoryName);
                categoryItem.addFavourite(new FavoriteItem(favoriteID,path,keyword,searchType));
                categories.put(categoryID,categoryItem);
            }
            else{

                for (String id :categories.keySet()){
                    if(categories.get(id).getName()==categoryName){
                        String favoriteID = LocalDateTime.now().toString();

                        stmt = conn.prepareStatement("INSERT INTO favorite(id,path,keyword,searchType,category,username) VALUES (?,?,?,?,?,?)");
                        stmt.setString(1,favoriteID);
                        stmt.setString(2,path);
                        stmt.setString(3,keyword);
                        stmt.setString(4,searchType);
                        stmt.setString(5,id);
                        stmt.setString(6,accountManager.getUsername().getValue());
                        stmt.executeUpdate();
                        stmt.close();

                        categories.get(id).addFavourite(new FavoriteItem(favoriteID,path,keyword,searchType));
                    }
                }
            }
            conn.close();
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public ObservableList<FavoriteItem> getFavorites(String categoryName){
        ObservableList<FavoriteItem> favorites = FXCollections.observableArrayList();
        for(String id:categories.keySet()){
            if(categories.get(id).getName().equals(categoryName)){
                return categories.get(id).getFavorites();
            }
        }
        return null;
    }

    public boolean deleteFav(FavoriteItem favoriteItem, String categoryName){
        try {
            conn=dbManager.connect();
            stmt = conn.prepareStatement("DELETE FROM favorite WHERE id=?");
            stmt.setString(1,favoriteItem.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }catch (Exception e){
            return false;
        }

        for(String id:categories.keySet()){
            if(categories.get(id).getName().equals(categoryName)){
                categories.get(id).deleteFavourite(favoriteItem);
            }
        }
        return true;
    }

    public void clearFav(String categoryName){
        String categoryID = getCategoryID(categoryName);

        try {
            conn=dbManager.connect();
            stmt = conn.prepareStatement("DELETE FROM favorite WHERE category=?");
            stmt.setString(1,categoryID);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        categories.get(categoryID).clearFavorites();
    }

    public void deleteCategory(String categoryName){
        String categoryID = getCategoryID(categoryName);
        clearFav(categoryName);

        try {
            conn=dbManager.connect();
            stmt = conn.prepareStatement("DELETE FROM category WHERE id=?");
            stmt.setString(1,categoryID);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        categories.remove(categoryID);
    }

    public String getCategoryID(String categoryName){
        for(String id:categories.keySet()){
            if(categories.get(id).getName().equals(categoryName)){
                return id;
            }
        }
        return null;
    }
}
