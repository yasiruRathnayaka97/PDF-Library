package Controllers;

import Models.AccountManager;
import Models.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private VBox vBoxUser;
    @FXML
    private Label lblUserUsername;

    private AccountManager accountManager;
    private WindowManager windowManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountManager = AccountManager.getInstance();
        windowManager = new WindowManager();
        lblUserUsername.textProperty().bind(accountManager.getUsername());
    }

    public void close(MouseEvent mouseEvent){
        windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
    }

    public void minimize(MouseEvent mouseEvent){
        windowManager.minimize((Stage) lblUserUsername.getScene().getWindow());
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
            windowManager.stageLoader("../ChangeWindow.fxml",2,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(MouseEvent mouseEvent){
        accountManager.deleteAccount();
        windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
        try {
            windowManager.stageLoader("../SignInSignUp.fxml",2,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signOut(MouseEvent mouseEvent){
        accountManager.signOut();
        windowManager.closeWindow((Stage) lblUserUsername.getScene().getWindow());
        try {
            windowManager.stageLoader("../SignInSignUp.fxml",2,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
