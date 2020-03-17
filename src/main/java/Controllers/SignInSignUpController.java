package Controllers;

import Models.AccountManager;
import Models.CommonStore;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignInSignUpController{
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

    public void showAlert(String message) throws Exception{
        //store message in common store
        commonStore = CommonStore.getInstance();
        commonStore.setMessage(message);

        //load alert
        commonStore.stageLoader("../AlertWindow.fxml",true,null);
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
            showAlert("Please enter required data");
        }
        //check for username has no spaces
        else if(textSignUpName.getText().contains(" ")){
            showAlert("User name cannot be contained spaces");
        }
        //check for password and confirm password is equal
        else if(!textSignUpPassword.getText().equals(textSignUpConfPassword.getText())){
            showAlert("Password and Confirm Password mismatch");
        }
        //check for password length is greater than or equal 7
        else if(textSignUpPassword.getText().length()<7){
            showAlert("Password is too short");
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
                showAlert("Select another userName");
            }
        }
    }

    //sign in data submit
    public void handleSignIn(MouseEvent mouseEvent) throws Exception {
        //check for empty fields
        if(textSignInName.getText().equals("") && textSignInPassword.getText().equals("")) {
            showAlert("Please enter required data");
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
                    showAlert("Wrong password!");
                }
            }
            else{
                showAlert("Wrong userName!");
            }
        }
    }
}
