package Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.io.File;


public class WindowManager {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private DirectoryChooser dirChooser;
    private IndexManager indexManager;
    private FileManager fileManager;

    public WindowManager(){
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

        stage.setTitle(title);

        if(title.equals("PDF Library")){
            stage.titleProperty().bind(indexManager.appTitleProperty());
        }

        if(borderType==1){
            stage.setMaximized(true);
            stage.initModality(Modality.WINDOW_MODAL);
        }
        else{
            stage.initModality(Modality.APPLICATION_MODAL);
            if(borderType==2) {
                stage.initStyle(StageStyle.DECORATED);
            }else if(borderType==3){
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
            }else if(borderType==4){
                stage.initStyle(StageStyle.UNDECORATED);
            }
        }

        //show stage
        stage.show();
    }

    public void closeWindow(Stage stage){
        this.stage = stage;
        stage.close();
    }

    public void dirWindow(Stage stage){
        dirChooser = new DirectoryChooser();

        //get selected directory to file
        indexManager.setDirPath(dirChooser.showDialog(stage).getAbsolutePath());

        //indexing
        try {
            stageLoader("../IndexingLoader.fxml",4,"");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void openSaveDialog(Stage stage){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Search");
            FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter(".txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(txtFilter);
            File file = fileChooser.showSaveDialog(stage);
            fileManager.saveFile(file.getAbsolutePath());
        }catch (Exception e){

        }
    }
}
