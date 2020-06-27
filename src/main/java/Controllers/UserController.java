package Controllers;

import Models.AccountManager;
import Models.AlertManager;
import Models.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private Label lblUserUsername;

    private AccountManager accountManager;
    private WindowManager windowManager;
    private AlertManager alertManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountManager = AccountManager.getInstance();
        windowManager = new WindowManager();
        lblUserUsername.textProperty().bind(accountManager.getUsername());
        alertManager = AlertManager.getInstance();

        //close when delete account
        accountManager.getUsername().addListener((observableValue, oldValue, newValue) -> {
            if(newValue.equals("")){
                windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
            }
        });
    }

    public void close(MouseEvent mouseEvent){
        windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
    }

    public void changeUsername(MouseEvent mouseEvent){
        accountManager.setIsChangeUsername(true);
        changeUsernamePassword();
    }

    public void changePassword(MouseEvent mouseEvent){
        accountManager.setIsChangeUsername(false);
        changeUsernamePassword();
    }

    public void changeUsernamePassword(){
        try {
            windowManager.stageLoader("../ChangeWindow.fxml",3,"PDF-Library");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(MouseEvent mouseEvent){
        alertManager.showConfirmWindow("Are you sure to delete account?","Delete");

        /*accountManager.deleteAccount(accountManager.getUsername().getValue());
        windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
        try {
            windowManager.stageLoader("../SignInSignUp.fxml",3,"Welcome to PDF Library");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void signOut(MouseEvent mouseEvent){
        accountManager.signOut();
        windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
        try {
            windowManager.stageLoader("../SignInSignUp.fxml",3,"Welcome to PDF Library");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
