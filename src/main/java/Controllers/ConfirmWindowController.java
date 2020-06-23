package Controllers;

import Models.AccountManager;
import Models.AlertManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmWindowController implements Initializable {
    @FXML
    private JFXButton btnCancel;
    @FXML
    private Label lbl;
    @FXML
    private JFXButton btnAction;

    private AlertManager alertManager;
    private WindowManager windowManager;
    private AccountManager accountManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertManager = AlertManager.getInstance();
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();
        lbl.setText(alertManager.getMessage());
        btnAction.setText(alertManager.getActionName());
    }



    //close alert window
    public void closeWindow(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnCancel.getScene().getWindow());
    }

    public void action(MouseEvent mouseEvent) {
        accountManager.deleteAccount(accountManager.getUsername().getValue());
        windowManager.closeWindow((Stage) btnAction.getScene().getWindow());
        try {
            windowManager.stageLoader("../SignInSignUp.fxml",3,"Welcome to PDF Library");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
