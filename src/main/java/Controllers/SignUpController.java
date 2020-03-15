package Controllers;
import Models.AccountManager;
import com.mongodb.DBObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignUpController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField rePassword;

    @FXML
    private Button submit;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        AccountManager ac= AccountManager.getAccount();
        Window owner = submit.getScene().getWindow();
        Stage Stage=(Stage)owner;
        if (userName.getText().isEmpty()) {
            System.out.println("Enter userName");
            return;
        }
        if (password.getText().isEmpty()) {
            System.out.println("Enter password");
            return;
        }
        if (rePassword.getText().isEmpty()) {
            System.out.println("Enter rePassword");
            return;
        }
        DBObject acObj=ac.retrieveOne("userName",userName.getText());
        if (acObj!=null){
            if(password.getText().equals(rePassword.getText())) {
                ac.setPassword(password.getText());
                ac.setUserName(userName.getText());
                ac.insertAccount();
                System.out.println("New Account created");

                Stage.close();

            }
            else {
                System.out.println("check passwords again");
            }
        }
        else{
            System.out.println("Select another userName");
        }
        return;
    }
}