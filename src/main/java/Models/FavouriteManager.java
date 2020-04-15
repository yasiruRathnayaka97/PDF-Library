package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class FavouriteManager{

    private static FavouriteManager instance;

    private FavouriteManager(){
        dbManager = DBManager.getInstance();
        accountManager = AccountManager.getInstance();
        favourite = new ArrayList<String[]>();
    };

    public static FavouriteManager getInstance(){
        if (instance == null)
            instance = new FavouriteManager();
        return instance;
    }

    private DBManager dbManager;
    private AccountManager accountManager;
    private ArrayList<String[]> favourite;
}
