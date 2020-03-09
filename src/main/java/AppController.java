import com.jfoenix.controls.JFXButton;
import com.sun.glass.ui.CommonDialogs;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppController {
    @FXML
    private JFXButton buttonSearch;
    @FXML
    private JFXButton buttonUser;
    @FXML
    private JFXButton buttonFavourite;
    @FXML
    private JFXButton buttonFolder;
    @FXML
    private JFXButton buttonHistory;
    @FXML
    private AnchorPane anchorPane;
    private Stage stage;
    private DirectoryChooser dirChooser;
    private File file;


    public void clickSearch(MouseEvent mouseEvent) {
        System.out.println("Search");
    }

    public void clickUser(MouseEvent mouseEvent) {
        System.out.println("Search");
    }

    public void clickFavourite(MouseEvent mouseEvent) {
        System.out.println("Search");
    }

    public void clickFolder(MouseEvent mouseEvent) {
        stage = (Stage)anchorPane.getScene().getWindow();
        dirChooser = new DirectoryChooser();
        file = dirChooser.showDialog(stage);
        if (file != null){
            System.out.println(file.getAbsoluteFile());
        }
    }

    public void clickHistory(MouseEvent mouseEvent) {
        System.out.println("Search");
    }
}
