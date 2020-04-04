package Controllers;

import Models.CommonStore;
import Models.WindowManager;
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
    private JFXButton btnClose;
    @FXML
    private Label lbl;

    Stage stage;

    CommonStore commonStore;
    private WindowManager windowManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commonStore = CommonStore.getInstance();
        windowManager = new WindowManager();

        //change lable
        lbl.setText(commonStore.getUsername());
    }

    public void clickClose(MouseEvent mouseEvent) {
        //close user stage
        stage= (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void handleSignOut(MouseEvent mouseEvent) throws Exception {
        //set username to null for sign out
        commonStore.setUsername(null);

        //close user stage
        stage= (Stage) btnClose.getScene().getWindow();
        stage.close();

        //load sign in sign up
        windowManager.stageLoader("../SignInSignUp.fxml",true,null);
    }
}
