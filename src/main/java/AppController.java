import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.scene.control.Label;
import java.io.File;
import javafx.stage.Modality;
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
    @FXML
    private Label lblFavourite;
    private Stage stage;
    private DirectoryChooser dirChooser;
    private File file;
    private File[] files;
    private Scene scene;
    private Parent root;


    public void clickSearch(MouseEvent mouseEvent) {
        System.out.println("Search");
    }

    public void clickUser(MouseEvent mouseEvent) throws Exception{
        root = FXMLLoader.load(getClass().getResource("User.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("User");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void clickFavourite(MouseEvent mouseEvent) throws Exception{
        root = FXMLLoader.load(getClass().getResource("Favourite.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Favourite");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void clickFolder(MouseEvent mouseEvent){
        stage = (Stage)anchorPane.getScene().getWindow();
        dirChooser = new DirectoryChooser();
        file = dirChooser.showDialog(stage);
        if (file != null){
            System.out.println(file.getAbsolutePath());
        }
    }

    public void clickHistory(MouseEvent mouseEvent) throws Exception {
        root = FXMLLoader.load(getClass().getResource("History.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("History");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void addFavourite(MouseEvent mouseEvent) {
        if(lblFavourite.getText().equals("\uEB51")){
            lblFavourite.setText("\uEB52");
        }else{
            lblFavourite.setText("\uEB51");
        }
    }
}
