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
    private AlertManager alertManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        alertManager = AlertManager.getInstance();
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
        try {
            windowManager.closeWindow((Stage) btnChange.getScene().getWindow());
            windowManager.stageLoader("../User.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleChange(MouseEvent mouseEvent) throws Exception {
        if(accountManager.isChangeUsername()) {
            String res = accountManager.changeUsername(txtChangeUsername.getText(), txtChangePassword.getText(), txtChangeNewUsername.getText());;

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
            String res = accountManager.changePassword(txtChangeUsername.getText(), txtChangePassword.getText(), txtChangeNewPassword.getText());;

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
