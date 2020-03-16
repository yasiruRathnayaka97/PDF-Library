package Controllers;

import com.jfoenix.controls.JFXButton;
import com.sun.glass.ui.CommonDialogs;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import javafx.scene.control.Label;
import java.io.File;
import java.util.List;

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
    private Scene scene;
    private Parent root;

    private FileChooser fileChooser;
    private File file;
    private List<File> files;

    private double xOffset = 0;
    private double yOffset = 0;

    public void clickSearch(MouseEvent mouseEvent) {
        System.out.println("Search");
    }

    public void clickUser(MouseEvent mouseEvent) throws Exception{
        root = FXMLLoader.load(getClass().getResource("../UserSignOut.fxml"));
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

    public void clickFavourite(MouseEvent mouseEvent) throws Exception{
        root = FXMLLoader.load(getClass().getResource("../Favourite.fxml"));
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

    public void clickFiles(MouseEvent mouseEvent){
        stage = (Stage)anchorPane.getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf","*.pdf"));
        files = fileChooser.showOpenMultipleDialog(stage);
        if (files != null){
            for(int i=0;i<files.size();i++){
                System.out.println(files.get(i).getAbsolutePath());
            }
        }
    }

    public void clickHistory(MouseEvent mouseEvent) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../History.fxml"));
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
