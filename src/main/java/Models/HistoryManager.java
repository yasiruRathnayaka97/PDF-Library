package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoryManager{
    private static HistoryManager instance;

    private HistoryManager(){
        dbManager = DBManager.getInstance();
        accountManager = AccountManager.getInstance();
        history = new ArrayList<HistoryItem>();
    };

    public static HistoryManager getInstance(){
        if (instance == null)
            instance = new HistoryManager();
        return instance;
    }

    private DBManager dbManager;
    private Connection conn;
    private Statement stmt;
    private String query;
    private AccountManager accountManager;
    private ArrayList<HistoryItem> history;
    private ResultSet resultSet;
    private HistoryItem historyItem;

    public void addHistory(String keyword, String type, String directory){
        try {
            conn = dbManager.connect();
            stmt = conn.createStatement();
            query = String.format("INSERT INTO history(id,keyword,type,directory,username) VALUES ('%s','%s','%s','%s','%s');", LocalDateTime.now().toString(),keyword,type,directory,accountManager.getUsername());
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            System.out.println("Added to history");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<HistoryItem> getHistory(){
        history.clear();
        try{
           conn = dbManager.connect();
           stmt = conn.createStatement();
           query= String.format("SELECT id,keyword,type,directory FROM history WHERE username='%s';",accountManager.getUsername());
           System.out.println(query);
           resultSet = stmt.executeQuery(query);
           while (resultSet.next()){
               historyItem = new HistoryItem(resultSet.getString("id"), resultSet.getString("keyword"),resultSet.getString("type"),resultSet.getString("directory"));
               history.add(historyItem);
           };
           resultSet.close();
           stmt.close();
           conn.close();
           return history;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //delete history one record from array list index
    public void deleteOne(String id){
        try{
            conn = dbManager.connect();
            stmt = conn.createStatement();
            query= String.format("DELETE FROM history WHERE id = '%s';",id);
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAll(){
        try{
            conn = dbManager.connect();
            stmt = conn.createStatement();
            query= String.format("DELETE FROM history WHERE username='%s';",accountManager.getUsername());
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            history.clear();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
