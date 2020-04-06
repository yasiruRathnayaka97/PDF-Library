package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private Label lbl;

    private WindowManager windowManager;
    private AccountManager accountManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();

        //change lable
        lbl.setText(accountManager.getUsername());
    }

    public void handleSignOut(MouseEvent mouseEvent) throws Exception {
        //set username to null for sign out
        accountManager.setUsername(null);
        accountManager.setPassword(null);

        //close user stage
        windowManager.closeWindow((Stage) lbl.getScene().getWindow());

        //load sign in sign up
        windowManager.stageLoader("../SignInSignUp.fxml",true,null);

        //user signout wenakota history favourite delete wenna ona

    }
}
