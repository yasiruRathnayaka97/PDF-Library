package Models;

import java.util.HashMap;

public class FavouriteManager extends DBManager{

    public FavouriteManager(){
        super("Favorites");
    }

public String insertFavourite(String path,String pdfName){
    HashMap<String, String> favDet = new HashMap();
    favDet.put("pdfName", pdfName);
    favDet.put("path", path);
    insert(favDet);
    return "Successful";
}

}
