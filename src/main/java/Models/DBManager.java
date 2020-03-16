package Models;

import com.mongodb.*;
import java.util.HashMap;

public  abstract class DBManager {
    private DB database;
    private String collectionName;

    public DBManager(String collectionName){
        MongoClient mongodb  = new MongoClient();
        this.collectionName=collectionName;
        //noinspection deprecation
        this.database= mongodb.getDB("PDF-Library");
    }
    protected String insert(HashMap<String,String> map){
        DBCollection collection = this.database.getCollection(this.collectionName);
        BasicDBObject document = new BasicDBObject();
        for (String key : map.keySet()) {
            document.append(key,map.get(key));

        }
        collection.insert(document);
        return "Successful";
    }
    public DBObject retrieveOne(String field, String value){
        DBObject obj = this.database.getCollection(this.collectionName).findOne(new BasicDBObject(field,value));
        return obj;
    }

    public Cursor retrieveAll(){
        Cursor c=this.database.getCollection(this.collectionName).find();
        return c;
    }

    public String delete(String field,String value){
        DBCollection collection = this.database.getCollection(this.collectionName);
        collection.remove(retrieveOne(field,value));
        return "Successful";
    }


}
