package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountManager{
    private static AccountManager instance;

    private AccountManager(){
        dbManager = DBManager.getInstance();
        alertManager = AlertManager.getInstance();
    };

    public static AccountManager getInstance(){
        if(instance == null)
            instance = new AccountManager();
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
    private AlertManager alertManager;
    private boolean changeUsername;

    public String register(String username, String password,String confPassword){
        try {
            if(username.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
                alertManager.showAlert("Please enter required data");
                return null;
            }
            //check for username has no spaces
            if(username.contains(" ")){
                alertManager.showAlert("User name cannot be contained spaces");
                return null;
            }
            //check for password and confirm password is equal
            if(!password.equals(confPassword)){
                alertManager.showAlert("Password and Confirm Password mismatch");
                return null;
            }
            //check for password length is greater than or equal 7
            if(password.length()<7){
                alertManager.showAlert("Password is too short");
                return null;
            }

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
                alertManager.showAlert("This username has been used!");
                return null;
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
            //check for empty fields
            if(username.isEmpty() || username.isEmpty()) {
                alertManager.showAlert("Please enter required data");
                return null;
            }

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
                System.out.println("Invalid username or password!");
                alertManager.showAlert("Invalid username or password!");
                return null;
            }
            System.out.println("No user");
            alertManager.showAlert("Invalid username or password!");
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

    public boolean changeUsername(String username,String password,String newUsername){
        try {
            if(username.isEmpty() || password.isEmpty() || newUsername.isEmpty()){
                System.out.println("Please enter required data");
                alertManager.showAlert("Please enter required data");
                return false;
            }
            if(newUsername.contains(" ")){
                System.out.println("User name cannot be contained spaces");
                alertManager.showAlert("User name cannot be contained spaces");
                return false;
            }

            if(login(username,password)=="success") {
                hasRegistered = true;
                conn=dbManager.connect();
                stmt = conn.createStatement();
                query = String.format("SELECT username FROM user WHERE username='%s';",newUsername);
                System.out.println(query);
                resultSet = stmt.executeQuery(query);
                if(!resultSet.next()){
                    hasRegistered = false;
                }
                resultSet.close();
                stmt.close();
                conn.close();
                if(hasRegistered){
                    alertManager.showAlert("This username has been used!");
                    return false;
                }

                conn = dbManager.connect();
                stmt = conn.createStatement();
                query = String.format("UPDATE user SET username='%s' WHERE username='%s' ;", newUsername, username);
                System.out.println(query);
                stmt.executeUpdate(query);
                stmt.close();
                conn.close();
                System.out.println("username is changed!");
                return true;
            }
            System.out.println("Invalid username or password!");
            alertManager.showAlert("Invalid username or password!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(String username,String password, String newPassword){
        try {
            if(username.isEmpty() || password.isEmpty() || newPassword.isEmpty()){
                System.out.println("Please enter required data");
                alertManager.showAlert("Please enter required data");
                return false;
            }
            if(newPassword.length()<7){
                alertManager.showAlert("Password is too short");
                return false;
            }

            if(login(username,password)=="success") {
                hasRegistered = true;
                conn=dbManager.connect();
                stmt = conn.createStatement();
                query = String.format("SELECT username FROM user WHERE username='%s';",newPassword);
                System.out.println(query);
                resultSet = stmt.executeQuery(query);
                if(!resultSet.next()){
                    hasRegistered = false;
                }
                resultSet.close();
                stmt.close();
                conn.close();
                if(hasRegistered){
                    alertManager.showAlert("This username has been used!");
                    return false;
                }

                conn = dbManager.connect();
                stmt = conn.createStatement();
                query =  String.format("UPDATE user SET password='%s' WHERE username='%s' ;",newPassword,username);
                System.out.println(query);
                stmt.executeUpdate(query);
                stmt.close();
                conn.close();
                System.out.println("password is changed!");
                return true;
            }
            System.out.println("Invalid username or password!");
            alertManager.showAlert("Invalid username or password!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setChangeUsername(boolean changeUsername) {
        this.changeUsername = changeUsername;
    }

    public boolean isChangeUsername() {
        return changeUsername;
    }

    public void deleteAccount(){
        try {
            conn = dbManager.connect();
            stmt = conn.createStatement();
            query =  String.format("DELETE FROM user WHERE username = '%s';",username);
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signOut(){
        username = null;
        password = null;
    }
}
