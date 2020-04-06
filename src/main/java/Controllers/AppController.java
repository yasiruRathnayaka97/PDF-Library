package Controllers;

import Models.AccountManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private SubScene subScene;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnUser;

    private WindowManager windowManager;
    private AccountManager accountManager;
    private boolean maximized;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();
        maximized = false;
        btnUser.setText(accountManager.getUsername());
    }

    public void handleUser(MouseEvent mouseEvent) {
        try {
            subScene.setRoot(FXMLLoader.load(getClass().getResource("../User.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDirectory(MouseEvent mouseEvent) {
        //windowManager.dirWindow();
    }

    public void handleFavourite(MouseEvent mouseEvent) {
        try {
            subScene.setRoot(FXMLLoader.load(getClass().getResource("../Favourite.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleHistory(MouseEvent mouseEvent) {
        try {
            subScene.setRoot(FXMLLoader.load(getClass().getResource("../History.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleResize(MouseEvent mouseEvent) {
        maximized = !maximized;
        windowManager.maximizedWindow((Stage) btnClose.getScene().getWindow(),maximized);
    }

    public void handleClose(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
    }

    public void handleMinimize(MouseEvent mouseEvent) {
        windowManager.minimizeWindow((Stage) btnClose.getScene().getWindow());
    }
}
