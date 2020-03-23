package Controllers;

import Models.AccountManager;
import Models.CommonStore;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commonStore = CommonStore.getInstance();
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
        //check for empty fields
        if(textSignUpName.getText().isEmpty() && textSignUpPassword.getText().isEmpty() && textSignUpConfPassword.getText().isEmpty()) {
            commonStore.showAlert("Please enter required data");
        }
        //check for username has no spaces
        else if(textSignUpName.getText().contains(" ")){
            commonStore.showAlert("User name cannot be contained spaces");
        }
        //check for password and confirm password is equal
        else if(!textSignUpPassword.getText().equals(textSignUpConfPassword.getText())){
            commonStore.showAlert("Password and Confirm Password mismatch");
        }
        //check for password length is greater than or equal 7
        else if(textSignUpPassword.getText().length()<7){
            commonStore.showAlert("Password is too short");
        }
        else{
            //data from db
            accountManager= AccountManager.getAccount();
            DBObject acObj=accountManager.retrieveOne("userName",textSignUpName.getText());
            if (acObj==null){
                accountManager.setUserName(textSignUpName.getText());
                accountManager.setPassword(textSignUpPassword.getText());
                accountManager.insertAccount();

                //close sign in sign up stage
                stage = (Stage) btnSignUp.getScene().getWindow();
                stage.close();

                //store username in common store
                commonStore = CommonStore.getInstance();
                commonStore.setUsername(textSignUpName.getText());

                //load app
                commonStore.stageLoader("../App.fxml",false,"PDF Library");
            }
            else{
                commonStore.showAlert("Select another userName");
            }
        }
    }

    //sign in data submit
    public void handleSignIn(MouseEvent mouseEvent) throws Exception {
        //check for empty fields
        if(textSignInName.getText().equals("") && textSignInPassword.getText().equals("")) {
            commonStore.showAlert("Please enter required data");
        }
        else{
            //data from db
            accountManager= AccountManager.getAccount();
            DBObject acObj=accountManager.retrieveOne("userName",textSignInName.getText());
            if (acObj!=null){
                if (acObj.get("password").equals(textSignInPassword.getText())){
                    accountManager.setPassword(textSignInName.getText());
                    accountManager.setUserName(textSignInName.getText());

                    //store username in common store
                    commonStore = CommonStore.getInstance();
                    commonStore.setUsername(textSignInName.getText());

                    //close sign in sign up stage
                    stage = (Stage) btnSignIn.getScene().getWindow();
                    stage.close();

                    //load app
                    commonStore.stageLoader("../App.fxml",false,"PDF Library");
                }
                else {
                    commonStore.showAlert("Wrong password!");
                }
            }
            else{
                commonStore.showAlert("Wrong userName!");
            }
        }
    }
}
