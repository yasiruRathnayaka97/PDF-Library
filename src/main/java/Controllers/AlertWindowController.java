package Controllers;

import Models.CommonStore;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertWindowController implements Initializable{
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnOk;
    @FXML
    private Label lbl;
    Stage stage;

    private CommonStore commonStore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get message from common store
        commonStore = CommonStore.getInstance();
        lbl.setText(commonStore.getMessage());
    }

    //close alert window
    public void closeWindow(MouseEvent mouseEvent) {
        stage= (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
}
