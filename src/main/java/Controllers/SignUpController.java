package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController {
    @FXML
    private JFXButton btnClose;
    private Stage stage;

    public void clickClose(MouseEvent mouseEvent) {
        stage= (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
