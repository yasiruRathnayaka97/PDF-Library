package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class UserController {
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnSignUp;
    @FXML
    private JFXButton btnSignIn;
    private Parent root;
    private Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;
    Stage stage;

    public void clickSignUp(MouseEvent mouseEvent) throws Exception{
        root = FXMLLoader.load(getClass().getResource("../SignUp.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("User");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.show();
    }

    public void clickSignIn(MouseEvent mouseEvent) throws Exception{
        root = FXMLLoader.load(getClass().getResource("../SignUp.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("User");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.show();
    }

    public void clickClose(MouseEvent mouseEvent) {
        stage= (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
