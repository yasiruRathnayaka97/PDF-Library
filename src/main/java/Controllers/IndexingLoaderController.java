package Controllers;

import Models.CommonStore;
import Models.IndexManager;
import com.jfoenix.controls.JFXSpinner;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class IndexingLoaderController implements Initializable {
    @FXML
    private JFXSpinner spinner;

    private Stage stage;

    private CommonStore commonStore;
    private IndexManager indexManager;
    private List<String> paths;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commonStore = CommonStore.getInstance();
        runTask();
    }

    private void runTask() {

        Task longTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                //start indexing
                commonStore.indexDirectory(commonStore.getFiles(),"./Index");
                return null;
            }
        };

        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                stage= (Stage) spinner.getScene().getWindow();
                stage.close();
            }
        });
        spinner.progressProperty().bind(longTask.progressProperty());

        new Thread(longTask).start();
    }

}

