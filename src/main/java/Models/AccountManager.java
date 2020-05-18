package Models;

import javafx.beans.property.SimpleStringProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AccountManager{
    private static AccountManager instance;

    private AccountManager(){
        dbManager = DBManager.getInstance();
        username = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
    };

    public static AccountManager getInstance(){
        if(instance == null)
            instance = new AccountManager();
        return instance;
    }

    private DBManager dbManager;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet resultSet;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private boolean hasRegistered;
    private boolean changeUsername;

    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isHasRegistered(String username){
        hasRegistered = true;
        try {
            conn=dbManager.connect();
            stmt = conn.prepareStatement("SELECT username FROM user WHERE username=?");
            stmt.setString(1,username);
            resultSet = stmt.executeQuery();
            if(!resultSet.next()){
                hasRegistered = false;
            }
            stmt.close();
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasRegistered;
    }

    public String register(String username, String password,String confPassword){
        try {
            if(username.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {

                return "Please enter required data";
            }
            //check for username has no spaces
            if(username.contains(" ")){
                return "User name cannot be contained spaces";
            }
            //check for password and confirm password is equal
            if(!password.equals(confPassword)){
                return "Password and Confirm Password mismatch";
            }
            //check for password length is greater than or equal 7
            if(password.length()<7){
                return "Password is too short";
            }

            this.username.set(username);
            this.password.set(password);

            if (isHasRegistered(username)){
                return "This username has been used!";
            }

            conn=dbManager.connect();
            stmt = conn.prepareStatement("INSERT INTO user(username, password) VALUES (?,?)");
            stmt.setString(1,username);
            stmt.setString(2,getMd5(password));
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            writeLastLogged();

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
            if(username.isEmpty() || password.isEmpty()) {
                return "Please enter required data";
            }

            this.username.set(username);
            hasRegistered = true;

            conn=dbManager.connect();
            stmt = conn.prepareStatement("SELECT password FROM user WHERE username= ?");
            stmt.setString(1,username);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                this.password.set(resultSet.getString("password"));
                hasRegistered = false;
            };
            resultSet.close();
            stmt.close();
            conn.close();
            if(!hasRegistered){
                if(this.password.getValue().equals(getMd5(password))){
                    System.out.println("Success!");
                    writeLastLogged();
                    return "success";
                }
                System.out.println("Invalid username or password!");
                return "Invalid username or password!";
            }
            System.out.println("No user");
            return "Invalid username or password!";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkLoggingCorrect(String username, String password){
        try {
            hasRegistered = true;

            conn=dbManager.connect();
            stmt = conn.prepareStatement("SELECT password FROM user WHERE username= ?");
            stmt.setString(1,username);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                this.password.set(resultSet.getString("password"));
                hasRegistered = false;
            };
            resultSet.close();
            stmt.close();
            conn.close();
            if(!hasRegistered){
                if(this.password.getValue().equals(password)){
                    System.out.println("Success!");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public SimpleStringProperty getUsername() {
        return username;
    }

    public SimpleStringProperty getPassword() {
        return password;
    }

    public String changeUsername(String username, String password, String newUsername){
        try {
            if(username.isEmpty() || password.isEmpty() || newUsername.isEmpty()){
                System.out.println("Please enter required data");
                return "Please enter required data";
            }
            if(newUsername.contains(" ")){
                System.out.println("User name cannot be contained spaces");
                return "User name cannot be contained spaces";
            }

            if(login(username,password)=="success") {
                if (isHasRegistered(newUsername)){
                    return "This username has been used!";
                }

                conn = dbManager.connect();
                stmt = conn.prepareStatement("UPDATE user SET username=? WHERE username=?");
                stmt.setString(1,newUsername);
                stmt.setString(2,username);
                stmt.executeUpdate();
                stmt.close();
                conn.close();
                System.out.println("username is changed!");
                this.username.set(newUsername);

                writeLastLogged();

                return "success";
            }
            System.out.println("Invalid username or password!");
            return "Invalid username or password!";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String changePassword(String username,String password, String newPassword, String confNewPassword){
        try {
            if(username.isEmpty() || password.isEmpty() || newPassword.isEmpty() || confNewPassword.isEmpty()){
                return "Please enter required data";
            }
            if(!newPassword.equals(confNewPassword)){
                return "New password and confirm new password mismatch!";
            }

            if(newPassword.length()<7){
                return "Password is too short!";
            }

            if(login(username,password)=="success") {

                conn = dbManager.connect();
                stmt = conn.prepareStatement("UPDATE user SET password=? WHERE username=?");
                stmt.setString(1,getMd5(newPassword));
                stmt.setString(2,username);
                stmt.executeUpdate();
                stmt.close();
                conn.close();
                System.out.println("password is changed!");
                this.password.set(getMd5(newPassword));

                writeLastLogged();

                return "success";
            }
            System.out.println("Invalid username or password!");
            return "Invalid username or password!";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setIsChangeUsername(boolean changeUsername) {
        this.changeUsername = changeUsername;
    }

    public boolean isChangeUsername() {
        return changeUsername;
    }

    public void deleteAccount(){
        try {
            conn = dbManager.connect();
            stmt = conn.prepareStatement("DELETE FROM user WHERE username = ?");
            stmt.setString(1,username.getValue());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signOut(){
        username.set("");
        password.set("");
        writeLastLogged();
    }

    public boolean readLastLogged(){
        try {

            File fXmlFile = new File("config.xml");

            if(!fXmlFile.exists()){
                System.out.println("No last log file");
                return false;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Element user = doc.getDocumentElement();

            username.set(user.getElementsByTagName("username").item(0).getTextContent());
            password.set(user.getElementsByTagName("password").item(0).getTextContent());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void writeLastLogged(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element rootElement = doc.createElement("lastLogged");

            Element element = doc.createElement("username");
            element.appendChild(doc.createTextNode(username.getValue()));
            rootElement.appendChild(element);

            element = doc.createElement("password");
            element.appendChild(doc.createTextNode(password.getValue()));
            rootElement.appendChild(element);

            doc.appendChild(rootElement);

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("config.xml")));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
