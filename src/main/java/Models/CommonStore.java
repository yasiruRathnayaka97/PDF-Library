package Models;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.List;

public class CommonStore {
    private static CommonStore single_instance = null;

    private Parent root;
    private Scene scene;
    private Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    private String message;
    private String username;
    private List<String> files;
    private IndexManager indexManager;

    private CommonStore(){
        indexManager = new IndexManager();
    };

    public static CommonStore getInstance(){
        if (single_instance == null)
            single_instance = new CommonStore();
        return single_instance;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getMessage() {
        return message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    //load stage
    public void stageLoader(String path,boolean appModel,String title) throws Exception{
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
        if(appModel) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
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

    public void showAlert(String message) throws Exception{
        //set message
        this.message = message;

        //load alert stage
        stageLoader("../AlertWindow.fxml",true,null);
    }

    public void indexDirectory(List<String> directoryPDFList, String dir){
        indexManager.indexDirectory(directoryPDFList, dir);
    }

    public IndexManager getIndexManager() {
        return indexManager;
    }

    public void loadingStage(String process) throws Exception {
        stageLoader("../IndexingLoader.fxml",true,null);
    }
}
