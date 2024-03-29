package Models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
    //create an object of SingleObject
    private static DBManager instance;

    //make the constructor private so that this class cannot be instantiated
    private DBManager() throws Exception {
        file = new File("pdfLibraryDB.mv.db");
        if (!file.exists()) {
            conn = connect();
            stmt = conn.createStatement();
            query = "CREATE TABLE user(username VARCHAR(30) PRIMARY KEY, password VARCHAR(32));" +
                    "CREATE TABLE category(id VARCHAR(30) PRIMARY KEY, name VARCHAR(30), username VARCHAR(30), FOREIGN KEY(username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE);"+
                    "CREATE TABLE favorite(id VARCHAR(30) PRIMARY KEY, path VARCHAR(500), keyword VARCHAR(200), searchType VARCHAR(10), category VARCHAR(30),username VARCHAR(30), FOREIGN KEY(username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY(category) REFERENCES category(id) ON UPDATE CASCADE ON DELETE CASCADE);" +
                    "CREATE TABLE history(id VARCHAR(30) PRIMARY KEY, keyword VARCHAR(200), type VARCHAR(10), directory VARCHAR(500), username VARCHAR(30), FOREIGN KEY(username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE);";
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            System.out.println("DB created!");
        }
    }

    //Get the only object available
    public static DBManager getInstance() {
        if (instance == null) {
            try {
                instance = new DBManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private Connection conn;
    private Statement stmt;
    private String query;
    private File file;

    public Connection connect() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./pdfLibraryDB", "sa", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}