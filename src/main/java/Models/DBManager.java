package Models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
    //create an object of SingleObject
    private static DBManager instance;

    static {
        try {
            instance = new DBManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection conn;
    private Statement stmt;
    private String query;
    private File file;

    //make the constructor private so that this class cannot be instantiated
    private DBManager() throws Exception{
        file=new File("pdfLibraryDB.mv.db");
        if(!file.exists()){
            conn = connect();
            stmt = conn.createStatement();
            query="CREATE TABLE user(username VARCHAR(30) PRIMARY KEY, password VARCHAR(30));" +
                    "CREATE TABLE favourites(id VARCHAR(30) PRIMARY KEY, path VARCHAR(500), username VARCHAR(30), FOREIGN KEY(username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE);"+
                    "CREATE TABLE history(id VARCHAR(30) PRIMARY KEY, keyword VARCHAR(200), type VARCHAR(10), directory VARCHAR(500), username VARCHAR(30), FOREIGN KEY(username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE);";
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            System.out.println("DB created!");
        };
    };

    //Get the only object available
    public static DBManager getInstance(){
        return instance;
    };

    public Connection connect(){
        try {
            Class.forName ("org.h2.Driver");
            conn = DriverManager.getConnection ("jdbc:h2:./pdfLibraryDB", "sa","");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*private DB database;
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
    }*/


}
