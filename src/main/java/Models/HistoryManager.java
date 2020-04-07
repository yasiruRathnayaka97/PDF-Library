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

    //delete history one record from arraylist index
    public void deleteOne(String id,Integer index){
        try{
            conn = dbManager.connect();
            stmt = conn.createStatement();
            query= String.format("DELETE FROM history WHERE id = '%s';",id);
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            history.remove(index);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAll(){
        try{
            conn = dbManager.connect();
            stmt = conn.createStatement();
            query= "DELETE FROM history;";
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            history.clear();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /*public HistoryManager() {
        super("History");
    }

    public String insertHistorySearchKeyword(String keyword, String dir) {
        HashMap<String, String> historyDetSK = new HashMap();
        historyDetSK.put("keyword", keyword);
        historyDetSK.put("dirName", dir);
        insert(historyDetSK);
        return "successful";
    }

    /*
    public String insertHistoryOpenedPDF(String pdfName, String path) {
        HashMap<String, String> historyDetO = new HashMap();
        historyDetO.put("pdfName", pdfName);
        historyDetO.put("path", path);
        insert(historyDetO);
        return "successful";
    }
    */
    //can use relevant method according to need.But can not use both.
    // If search keywords add to the history use insertHistorySearchKeyword().
    //else Paths of opened file add to the history use insertHistoryOpenedPDF().*/
}
