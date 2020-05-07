package Models;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


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
    public void stageLoader(String path,boolean borderless,String title) throws Exception{
        //load stage
        root = FXMLLoader.load(getClass().getResource(path));
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);

        //set title
        if(title!=null){
            stage.getIcons().add(new Image("Icon.png"));
            stage.setTitle(title);
        }

        if(title=="PDF Library"){
            stage.initModality(Modality.WINDOW_MODAL);
        }else{
            stage.initModality(Modality.APPLICATION_MODAL);
        }

        //set modality and boarder style
        if(borderless) {
            stage.initStyle(StageStyle.TRANSPARENT);

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
        }

        //show stage
        stage.show();
    }

    public void closeWindow(Stage stage){
        this.stage = stage;
        stage.close();
    }

    public void dirWindow(){
        //stage = (Stage)anchorPane.getScene().getWindow();
        stage = new Stage();
        dirChooser = new DirectoryChooser();//metana application mode ekata modality set karanna ona

        //get selected directory to file
        indexManager.setDirPath(dirChooser.showDialog(stage).getAbsolutePath());

        //indexing
        try {
            stageLoader("../IndexingLoader.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void minimize(Stage stage){
        this.stage = stage;
        stage.setIconified(true);
    }
}
