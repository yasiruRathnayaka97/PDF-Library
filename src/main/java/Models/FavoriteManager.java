package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private Statement stmt;
    private String query;
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

    public void getCategoryFromDB(){
        try {
            conn=dbManager.connect();
            stmt = conn.createStatement();

            query = String.format("SELECT id,name FROM Category WHERE username='%s'",accountManager.getUsername());
            System.out.println(query);
            resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                categories.put(resultSet.getString("id"),new CategoryItem(resultSet.getString("name")));
            };

            for (String id : categories.keySet()) {
                query = String.format("SELECT id,path,keyword,searchType FROM favorite WHERE username='%s' AND category='%s'",accountManager.getUsername(),id);
                System.out.println(query);
                resultSet = stmt.executeQuery(query);
                while(resultSet.next()){
                    categories.get(id).addFavourite(new FavoriteItem(resultSet.getString("id"), resultSet.getString("path"), resultSet.getString("keyword"), resultSet.getString("searchType")));
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

    public void addFavorite(boolean newCategory,String categoryName){
        try {
            conn=dbManager.connect();
            stmt = conn.createStatement();

            if(newCategory){
                String categoryID = LocalDateTime.now().toString();
                String favoriteID = categoryID;
                query= String.format("INSERT INTO category(id,name,username) VALUES ('%s','%s','%s');" +
                            "INSERT INTO favorite(id,path,keyword,searchType,category,username) VALUES ('%s','%s','%s','%s','%s','%s');",
                        categoryID,categoryName,accountManager.getUsername(),
                        favoriteID,path,keyword,searchType,categoryID,accountManager.getUsername());
                System.out.println(query);
                stmt.executeUpdate(query);

                CategoryItem categoryItem = new CategoryItem(categoryName);
                categoryItem.addFavourite(new FavoriteItem(favoriteID,path,keyword,searchType));
                categories.put(categoryID,categoryItem);
            }
            else{

                for (String id :categories.keySet()){
                    if(categories.get(id).getName()==categoryName){
                        String favoriteID = LocalDateTime.now().toString();
                        query = String.format("INSERT INTO favorite(id,path,keyword,searchType,category,username) VALUES ('%s','%s','%s','%s','%s','%s');",favoriteID,path,keyword,searchType,id,accountManager.getUsername());
                        System.out.println(query);
                        categories.get(id).addFavourite(new FavoriteItem(favoriteID,path,keyword,searchType));
                    }
                }
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
