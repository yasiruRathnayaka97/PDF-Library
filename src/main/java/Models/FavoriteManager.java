package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteManager {

    private static FavoriteManager instance;

    private FavoriteManager(){
        dbManager = DBManager.getInstance();
        accountManager = AccountManager.getInstance();
        categories = new HashMap<String, CategoryItem>();
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

    public void getCategoryFromDB(){
        try {
            conn=dbManager.connect();
            stmt = conn.createStatement();
            query = String.format("SELECT * FROM Category WHERE username='%s'",accountManager.getUsername());
            resultSet = stmt.executeQuery(query);
            if(resultSet.next()){
                categories.put(resultSet.getString("id"),new CategoryItem(resultSet.getString("name")));
            };
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, CategoryItem> getCategories() {
        return categories;
    }
}
