
import com.mongodb.*;
import java.util.ArrayList;
import java.util.HashMap;

public  abstract class DBManager {
    private DB database;

    public DBManager(){
        MongoClient mongodb  = new MongoClient();
        //noinspection deprecation
        this.database= mongodb.getDB("PDF-Library");
    }
    public String insert(HashMap<String,String> map,String collectionName){
        DBCollection collection = this.database.getCollection(collectionName);
        BasicDBObject document = new BasicDBObject();
        for (String key : map.keySet()) {
            document.append(key,map.get(key));

        }
        collection.insert(document);
        return "Successful";
    }
    public ArrayList<Object> retrieve(String field, String value, String collection){
        ArrayList<Object> arr=new ArrayList<Object>();
        DBCursor cursor = this.database.getCollection(collection).find(new BasicDBObject(field, value));
        while (cursor.hasNext()) {
            arr.add(cursor.next());

        }
        return arr;
    }
    public Boolean isExist(String field, String value, String collection) {
        if (retrieve(field, value,collection).size()==0){
            return false;
        }

        else{
            return true;
        }
    }

}
