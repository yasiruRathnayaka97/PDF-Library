package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountManager{
    private static AccountManager instance=new AccountManager();

    private AccountManager(){
        dbManager = DBManager.getInstance();
    };

    public static AccountManager getInstance(){
        return instance;
    }

    private DBManager dbManager;
    private Connection conn;
    private Statement stmt;
    private ResultSet resultSet;
    private String query;
    private String username;
    private String password;
    private boolean hasRegistered;

    public String register(String username, String password){
        try {
            this.username = username;
            this.password = password;
            hasRegistered = true;

            conn=dbManager.connect();
            stmt = conn.createStatement();
            query = String.format("SELECT username FROM user WHERE username='%s';",username);
            resultSet = stmt.executeQuery(query);
            if(!resultSet.next()){
                hasRegistered = false;
            }
            resultSet.close();
            stmt.close();
            conn.close();
            if(hasRegistered){
                System.out.println("This username has been used!");
                return "This username has been used!";
            }

            conn=dbManager.connect();
            stmt = conn.createStatement();
            query =  String.format("INSERT INTO user(username, password) VALUES ('%s','%s');",username,password);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            System.out.println("success");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String login(String username, String password){
        try {
            this.username = username;
            hasRegistered = true;

            conn=dbManager.connect();
            stmt = conn.createStatement();
            query =  String.format("SELECT password FROM user WHERE username= '%s';",username);
            resultSet = stmt.executeQuery(query);
            if(resultSet.next()){
                this.password = resultSet.getString("password");
                hasRegistered = false;
            };
            resultSet.close();
            stmt.close();
            conn.close();
            if(!hasRegistered){
                if(this.password.equals(password)){
                    System.out.println("Success!");
                    return "success";
                }
                System.out.println("Invalid Password!");
                return null;
            }
            System.out.println("not success");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
