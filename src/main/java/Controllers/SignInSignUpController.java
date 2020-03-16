package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void clickClose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void showSignUp(MouseEvent mouseEvent) {
        signUpPane.toFront();
    }

    public void showSignIn(MouseEvent mouseEvent) {
        signInPane.toFront();
    }

    public void handleSignUp(MouseEvent mouseEvent) throws Exception {
        if(textSignUpName.getText().equals("")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../AlertWindow.fxml"));
            root= loader.load();
            scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);

            AlertWindowController alertWindowController = loader.getController();
            alertWindowController.setMessage("please enter User Name");

            stage.show();



        }
        System.out.println(textSignUpName.getText());
    }
}
