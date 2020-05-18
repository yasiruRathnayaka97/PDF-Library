package Controllers;

import Models.IndexManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXSpinner;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexingLoaderController implements Initializable {
    @FXML
    private JFXSpinner spinner;

    private IndexManager indexManager;
    private WindowManager windowManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        indexManager = IndexManager.getInstance();
        windowManager = new WindowManager();
        runTask();
    }

    private void runTask() {

        Task longTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                //start indexing
                indexManager.indexDirectory();
                return null;
            }
        };

        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                windowManager.closeWindow((Stage) spinner.getScene().getWindow());
            }
        });
        spinner.progressProperty().bind(longTask.progressProperty());

        new Thread(longTask).start();
    }

}

