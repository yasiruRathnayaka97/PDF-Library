package Controllers;

import Models.AlertManager;
import Models.WindowManager;
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

    private AlertManager alertManager;
    private WindowManager windowManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get message from common store
        alertManager = AlertManager.getInstance();
        windowManager = new WindowManager();
        lbl.setText(alertManager.getMessage());
    }

    //close alert window
    public void closeWindow(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnOk.getScene().getWindow());
    }
}
