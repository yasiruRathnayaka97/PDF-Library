package Controllers;

import Models.AccountManager;
import Models.AlertManager;
import Models.CommonStore;
import Models.WindowManager;
import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInSignUpController implements Initializable {
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnSignUpTab;
    @FXML
    private JFXButton btnSignInTab;
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private AnchorPane signInPane;

    @FXML
    private JFXTextField textSignUpName;
    @FXML
    private JFXPasswordField textSignUpPassword;
    @FXML
    private JFXPasswordField textSignUpConfPassword;
    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXTextField textSignInName;
    @FXML
    private JFXPasswordField textSignInPassword;

    private AccountManager accountManager;
    private WindowManager windowManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountManager = AccountManager.getInstance();
        windowManager = new WindowManager();
        btnSignUpTab.setDisable(true);
    }

    //exit
    public void handleClose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    //show sign up form
    public void showSignUp(MouseEvent mouseEvent) {
        signUpPane.toFront();
        new FadeIn(signUpPane).play();
        btnSignUpTab.setDisable(true);
        btnSignInTab.setDisable(false);
    }

    //show sign in form
    public void showSignIn(MouseEvent mouseEvent) {
        signInPane.toFront();
        new FadeIn(signInPane).play();
        btnSignInTab.setDisable(true);
        btnSignUpTab.setDisable(false);
    }

    //sign up data submit
    public void handleSignUp(MouseEvent mouseEvent) throws Exception {
        if(accountManager.register(textSignUpName.getText(),textSignUpPassword.getText(),textSignUpConfPassword.getText())=="success"){
            System.out.println("Successfully Sign Up!");
            windowManager.stageLoader("../App.fxml",true,null);
            windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
        }
    }

    //sign in data submit
    public void handleSignIn(MouseEvent mouseEvent) throws Exception {
        if(accountManager.login(textSignInName.getText(),textSignInPassword.getText())=="success"){
            System.out.println("Successfully login!");
            windowManager.stageLoader("../App.fxml",true,null);
            windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
        }
    }
}
