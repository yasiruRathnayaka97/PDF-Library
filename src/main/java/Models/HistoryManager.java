package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

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
    private PreparedStatement stmt;
    private AccountManager accountManager;
    private ArrayList<HistoryItem> history;
    private ResultSet resultSet;
    private HistoryItem historyItem;

    public boolean addHistory(String keyword, String type, String directory){
        try {
            conn = dbManager.connect();
            stmt = conn.prepareStatement("INSERT INTO history(id,keyword,type,directory,username) VALUES (?,?,?,?,?)");
            stmt.setString(1,LocalDateTime.now().toString());
            stmt.setString(2,keyword);
            stmt.setString(3,type);
            stmt.setString(4,directory);
            stmt.setString(5,accountManager.getUsername().getValue());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Added to history");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<HistoryItem> getHistory(){
        history.clear();
        try{
           conn = dbManager.connect();
           stmt = conn.prepareStatement("SELECT id,keyword,type,directory FROM history WHERE username=?");
           stmt.setString(1,accountManager.getUsername().getValue());
           resultSet = stmt.executeQuery();
           while (resultSet.next()){
               historyItem = new HistoryItem(resultSet.getString("id"), resultSet.getString("keyword"),resultSet.getString("type"),resultSet.getString("directory"));
               history.add(historyItem);
           };
           resultSet.close();
           stmt.close();
           conn.close();

            Collections.reverse(history);
           
           return  history;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //delete history one record from array list index
    public boolean deleteOne(String id){
        try{
            conn = dbManager.connect();
            stmt = conn.prepareStatement("DELETE FROM history WHERE id = ?");
            stmt.setString(1,id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAll(){
        try{
            conn = dbManager.connect();
            stmt = conn.prepareStatement("DELETE FROM history WHERE username=?");
            stmt.setString(1,accountManager.getUsername().getValue());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            history.clear();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
