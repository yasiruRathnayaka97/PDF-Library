package Controllers;
import Models.AccountManager;
import com.mongodb.DBObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.io.IOException;

public class SignInController{
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    protected void handleSignInButtonAction(ActionEvent event) {
        AccountManager ac= AccountManager.getAccount();
        Window owner = signIn.getScene().getWindow();
        Stage primaryStage=(Stage)owner;
        if (userName.getText().isEmpty()) {
            return;
        }
        if (password.getText().isEmpty()) {
            return;
        }
        DBObject acObj=ac.retrieveOne("userName",userName.getText());
        if (acObj!=null){
             if (acObj.get("password")==password.getText()){
                 System.out.println("signIn");
                 ac.setPassword(password.getText());
                 ac.setUserName(userName.getText());
                 primaryStage.close();

             }
            else {
                 System.out.println("wrong password");
             }
        }
        else{
            System.out.println("wrong userName");
        }
        return;


    }
    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUp.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Window owner = signIn.getScene().getWindow();
            Stage primaryStage=(Stage)owner;
            primaryStage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}






