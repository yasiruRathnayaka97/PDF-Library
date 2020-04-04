package Models;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


public class WindowManager {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private double xOffset;
    private double yOffset;

    public WindowManager(){
        xOffset = 0;
        yOffset = 0;
    };

    //load stage
    public void stageLoader(String path,boolean boarderless,String title) throws Exception{
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
        if(boarderless) {
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
}
