package Controllers;

import Models.AccountManager;
import Models.AlertManager;
import Models.CommonStore;
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
    @FXML
    private JFXButton btnSignIn;

    private Stage stage;

    private AccountManager accountManager;
    private CommonStore commonStore;
    private AlertManager alertManager;
    private WindowManager windowManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commonStore = CommonStore.getInstance();
        accountManager = AccountManager.getInstance();
        alertManager = AlertManager.getInstance();
        windowManager = new WindowManager();
    }

    //exit
    public void clickClose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    //show sign up form
    public void showSignUp(MouseEvent mouseEvent) {
        signUpPane.toFront();
    }

    //show sign in form
    public void showSignIn(MouseEvent mouseEvent) {
        signInPane.toFront();
    }

    //sign up data submit
    public void handleSignUp(MouseEvent mouseEvent) throws Exception {
        if(textSignUpName.getText().isEmpty() && textSignUpPassword.getText().isEmpty() && textSignUpConfPassword.getText().isEmpty()) {
            alertManager.showAlert("Please enter required data");
        }
        //check for username has no spaces
        else if(textSignUpName.getText().contains(" ")){
            alertManager.showAlert("User name cannot be contained spaces");
        }
        //check for password and confirm password is equal
        else if(!textSignUpPassword.getText().equals(textSignUpConfPassword.getText())){
            alertManager.showAlert("Password and Confirm Password mismatch");
        }
        //check for password length is greater than or equal 7
        else if(textSignUpPassword.getText().length()<7){
            alertManager.showAlert("Password is too short");
        }
        else{
            if(accountManager.register(textSignUpName.getText(),textSignUpPassword.getText())=="success"){
                System.out.println("Successfully Sign Up!");
                windowManager.stageLoader("../App.fxml",false,"PDF Library");
            }
            else if(accountManager.register(textSignUpName.getText(),textSignUpPassword.getText())=="This username has been used!"){
                alertManager.showAlert("This username has been used!");
                System.out.println("This username has been used!");
            }
        }
    }

    //sign in data submit
    public void handleSignIn(MouseEvent mouseEvent) throws Exception {
        //check for empty fields
        if(textSignInName.getText().equals("") && textSignInPassword.getText().equals("")) {
            alertManager.showAlert("Please enter required data");

        }
        else{
            if(accountManager.login(textSignInName.getText(),textSignInPassword.getText())=="success"){
                windowManager.stageLoader("../App.fxml",false,"PDF Library");
            }
            else{
                alertManager.showAlert("Invalid username or password!");
            }
        }
    }
}
