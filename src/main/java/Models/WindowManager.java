package Models;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import java.io.File;


public class WindowManager {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private double xOffset;
    private double yOffset;
    private DirectoryChooser dirChooser;
    private IndexManager indexManager;
    private FileManager fileManager;

    public WindowManager(){
        xOffset = 0;
        yOffset = 0;
        indexManager = IndexManager.getInstance();
        fileManager = new FileManager();
    };

    //load stage
    public void stageLoader(String path,int borderType,String title) throws Exception{
        //load stage
        root = FXMLLoader.load(getClass().getResource(path));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("Icon.png"));

        //set title
        if(title!=null){
            stage.setTitle(title);
            if(title.equals("PDF Library")){
                stage.setMaximized(true);
            }
        }

        if(title=="PDF Library"){
            stage.initModality(Modality.WINDOW_MODAL);
        }else{
            stage.initModality(Modality.APPLICATION_MODAL);
        }

        //set modality and boarder style
        if(borderType==2) {
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
        }else if(borderType==3){
            stage.initStyle(StageStyle.UNDECORATED);
        }

        //show stage
        stage.show();
    }

    public void closeWindow(Stage stage){
        this.stage = stage;
        stage.close();
    }

    public void dirWindow(){
        stage = new Stage();
        dirChooser = new DirectoryChooser();//TODO metana application mode ekata modality set karanna ona

        //get selected directory to file
        indexManager.setDirPath(dirChooser.showDialog(stage).getAbsolutePath());

        //indexing
        try {
            stageLoader("../IndexingLoader.fxml",3,null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void minimize(Stage stage){
        this.stage = stage;
        stage.setIconified(true);
    }

    public void openSaveDialog(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Search");
            FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter(".txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(txtFilter);
            File file = fileChooser.showSaveDialog(new Stage());//TODO metana application mode ekata modality set karanna ona
            fileManager.saveFile(file.getAbsolutePath());
        }catch (Exception e){

        }
    }
}
