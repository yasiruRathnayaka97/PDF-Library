package Controllers;

import Models.CommonStore;
import Models.IndexManager;
import Models.SearchManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sun.glass.ui.CommonDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import javafx.scene.control.Label;
import java.io.File;
import java.net.URL;
import java.util.*;

public class AppController implements Initializable {
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
    @FXML
    private JFXComboBox dropDownSearchType;

    private Stage stage;
    private DirectoryChooser dirChooser;
    private Scene scene;
    private Parent root;

    private FileChooser fileChooser;
    private File file;
    private List<File> files;
    private ObservableList<String> searchTypes;
    private String path;

    private double xOffset = 0;
    private double yOffset = 0;



    private CommonStore commonStore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commonStore = CommonStore.getInstance();

        //set search types items
        searchTypes = FXCollections.observableArrayList("content","pdfName","path");
        dropDownSearchType.setItems(searchTypes);
    }

    //handle search operation
    public void clickSearch(MouseEvent mouseEvent) {
        if(path.equals(null)){

        }

        //create new list
        List<String> paths = new ArrayList<String>();
        paths.add("C:\\Users\\DilankaRathnasiri\\Documents\\A");

        //call index manager
        IndexManager indexManager = new IndexManager();
        indexManager.indexDirectory(paths,"C:\\Users\\DilankaRathnasiri\\Documents\\A");
        //call search manager
        SearchManager searchManager = new SearchManager(indexManager);
        List<String> abc = searchManager.search("C:\\Users\\DilankaRathnasiri\\Documents\\A","pdfName","n");
        for(int i=0;i<abc.size();i++){
            System.out.println(abc.get(i));
        }
    }

    //open user stage
    public void clickUser(MouseEvent mouseEvent) throws Exception{
            commonStore.stageLoader("../User.fxml",true,null);
    }

    //load favourite window
    public void clickFavourite(MouseEvent mouseEvent) throws Exception{
        commonStore.stageLoader("../Favourite.fxml",false,"Favourite");
    }

    //load directory chooser
    public void clickFolder(MouseEvent mouseEvent){
        stage = (Stage)anchorPane.getScene().getWindow();
        dirChooser = new DirectoryChooser();
        file = dirChooser.showDialog(stage);
        if (file != null){
            System.out.println(file.getAbsolutePath());
        }
    }

    //load file chooser
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

    //load history window
    public void clickHistory(MouseEvent mouseEvent) throws Exception {
        commonStore.stageLoader("../History.fxml",false,"History");
    }

    //add favourite
    public void addFavourite(MouseEvent mouseEvent) {
        if(lblFavourite.getText().equals("\uEB51")){
            lblFavourite.setText("\uEB52");
        }else{
            lblFavourite.setText("\uEB51");
        }
    }

    public void openFile(MouseEvent mouseEvent) {
    }
}
