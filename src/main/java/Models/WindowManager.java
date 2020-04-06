package Models;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.List;


public class WindowManager {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private double xOffset;
    private double yOffset;
    //private DirectoryChooser dirChooser;
    //private File file;
    //private List<String> files;
    //private FileManager fileManager;
    //private CommonStore commonStore;

    public WindowManager(){
        xOffset = 0;
        yOffset = 0;
        //fileManager = new FileManager();
        //commonStore = CommonStore.getInstance();
    };

    //load stage
    public void stageLoader(String path,boolean borderless,String title) throws Exception{
        //load stage
        root = FXMLLoader.load(getClass().getResource(path));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);

        //set title
        if(title!=null){
            stage.setTitle(title);
        }

        //set modality and boarder style
        if(borderless) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
        }

        //set stage movable
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

        //show stage
        stage.show();
    }

    public void minimizeWindow(Stage stage){
        this.stage = stage;
        stage.setIconified(true);
    }

    public void maximizedWindow(Stage stage, boolean maximized){
        this.stage = stage;
        stage.setMaximized(maximized);
    }

    public void closeWindow(Stage stage){
        this.stage = stage;
        stage.close();
    }

    /*public void dirWindow(){
        this.stage = new Stage();
        dirChooser = new DirectoryChooser();

        //get selected directory to file
        file = dirChooser.showDialog(stage);
        files = fileManager.getAllPDFUnderDir(file.getAbsolutePath());

        //save paths to common store
        commonStore.setFiles(files);

        //indexing
        try {
            stageLoader("../IndexingLoader.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
