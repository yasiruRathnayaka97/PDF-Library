package Controllers;

import Models.AccountManager;
import Models.AlertManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeWindowController implements Initializable {
    @FXML
    private JFXButton btnChange;
    @FXML
    private AnchorPane paneUsername;
    @FXML
    private AnchorPane panePassword;
    @FXML
    private JFXTextField txtPaneUserUsername;
    @FXML
    private JFXPasswordField txtPaneUserPassword;
    @FXML
    private JFXTextField txtNewUsername;
    @FXML
    private JFXTextField txtPanePwdUsername;
    @FXML
    private JFXPasswordField txtPanePwdPassword;
    @FXML
    private JFXPasswordField txtNewPassword;
    @FXML
    private JFXPasswordField txtConfNewPassword;

    private WindowManager windowManager;
    private AccountManager accountManager;
    private AlertManager alertManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        alertManager = AlertManager.getInstance();
        accountManager = AccountManager.getInstance();
        if(accountManager.isChangeUsername()){
            paneUsername.toFront();
        }else{
            panePassword.toFront();
        }
    }

    //close window
    public void closeWindow(MouseEvent mouseEvent) {
        try {
            windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
            windowManager.stageLoader("../User.fxml",3,"User Settings");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleChange(MouseEvent mouseEvent) throws Exception {
        if(accountManager.isChangeUsername()) {
            String res = accountManager.changeUsername(txtPaneUserUsername.getText(), txtPaneUserPassword.getText(), txtNewUsername.getText());;

            if(res.equals("success")){
                alertManager.showAlert("username is changed!");
                windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
            }
            else if(res.equals("Please enter required data")){
                alertManager.showAlert("Please enter required data");
            }
            else if(res.equals("User name cannot be contained spaces")){
                alertManager.showAlert("User name cannot be contained spaces");
            }
            else if(res.equals("This username has been used!")){
                alertManager.showAlert("This username has been used!");
            }
            else if(res.equals("Invalid username or password!")){
                alertManager.showAlert("Invalid username or password!");
            }
        }
        else {
            String res = accountManager.changePassword(txtPanePwdUsername.getText(), txtPanePwdPassword.getText(), txtNewPassword.getText(), txtConfNewPassword.getText());;

            if(res.equals("success")){
                alertManager.showAlert("password is changed!");
                windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
            }
            else{
                alertManager.showAlert(res);
            }
        }
    }
}
