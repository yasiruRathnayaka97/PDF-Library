package Controllers;

import Models.AccountManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeWindowController implements Initializable {
    @FXML
    private JFXButton btnChange;
    @FXML
    private JFXTextField txtChangeUsername;
    @FXML
    private JFXPasswordField txtChangePassword;
    @FXML
    private JFXTextField txtChangeNewUsername;
    @FXML
    private JFXPasswordField txtChangeNewPassword;

    private WindowManager windowManager;
    private AccountManager accountManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();
        if(accountManager.isChangeUsername()){
            txtChangeNewUsername.setVisible(true);
            txtChangeNewPassword.setVisible(false);
        }else{
            txtChangeNewUsername.setVisible(false);
            txtChangeNewPassword.setVisible(true);
        }
    }

    //close window
    public void closeWindow(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
    }


    public void handleChange(MouseEvent mouseEvent) {
        if(accountManager.isChangeUsername()) {
            accountManager.changeUsername(txtChangeUsername.getText(), txtChangePassword.getText(), txtChangeNewUsername.getText());
            windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
        }
        else {
            accountManager.changePassword(txtChangeUsername.getText(), txtChangePassword.getText(), txtChangeNewPassword.getText());
            windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
        }
    }
}
