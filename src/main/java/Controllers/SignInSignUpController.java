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
    private AlertManager alertManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountManager = AccountManager.getInstance();
        windowManager = new WindowManager();
        alertManager = AlertManager.getInstance();
        btnSignUpTab.setDisable(true);
    }

    //exit
    public void handleClose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    //show sign up form
    public void showSignUp(MouseEvent mouseEvent) {
        signUpPane.toFront();
        btnSignUpTab.setDisable(true);
        btnSignInTab.setDisable(false);
    }

    //show sign in form
    public void showSignIn(MouseEvent mouseEvent) {
        signInPane.toFront();
        btnSignInTab.setDisable(true);
        btnSignUpTab.setDisable(false);
    }

    //sign up data submit
    public void handleSignUp(MouseEvent mouseEvent) throws Exception {
        String res = accountManager.register(textSignUpName.getText(),textSignUpPassword.getText(),textSignUpConfPassword.getText());
        if(res.equals("success")){
            System.out.println("Successfully Sign Up!");
            windowManager.stageLoader("../App.fxml",false,"PDF Library");
            windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
        }
        else if(res.equals("Please enter required data")){
            alertManager.showAlert("Please enter required data");
        }
        else if(res.equals("User name cannot be contained spaces")){
            alertManager.showAlert("User name cannot be contained spaces");
        }
        else if(res.equals("Password and Confirm Password mismatch")){
            alertManager.showAlert("Password and Confirm Password mismatch");
        }
        else if(res.equals("Password is too short")){
            alertManager.showAlert("Password is too short");
        }
        else if(res.equals("This username has been used!")){
            alertManager.showAlert("This username has been used!");
        }
    }

    //sign in data submit
    public void handleSignIn(MouseEvent mouseEvent) throws Exception {
        if(accountManager.login(textSignInName.getText(),textSignInPassword.getText())=="success"){
            System.out.println("Successfully login!");
            windowManager.stageLoader("../App.fxml",false,"PDF Library");
            windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
        }
    }
}
