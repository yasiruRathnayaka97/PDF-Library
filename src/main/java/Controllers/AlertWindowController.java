package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertWindowController{
    @FXML
    private JFXButton btnOk;
    @FXML
    private Label lbl;
    Stage stage;

    public void setMessage(String message) {
        lbl.setText(message);
    }

    public void handleOk(MouseEvent mouseEvent) {
        stage= (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
}
