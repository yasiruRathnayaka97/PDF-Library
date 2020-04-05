package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class FavouriteManager{

    private static FavouriteManager instance=new FavouriteManager();

    private FavouriteManager(){
        dbManager = DBManager.getInstance();
        accountManager = AccountManager.getInstance();
        favourite = new ArrayList<String[]>();
    };

    public static FavouriteManager getInstance(){
        return instance;
    }

    private DBManager dbManager;
    private AccountManager accountManager;
    private ArrayList<String[]> favourite;

    /*public FavouriteManager(){
        super("Favorites");
    }

public String insertFavourite(String path,String keyword,String searchType){
    HashMap<String, String> favDet = new HashMap();
    favDet.put("path", path);
    favDet.put("keyword", keyword);
    favDet.put("searchType", searchType);
    insert(favDet);
    return "Successful";
}*/

}
