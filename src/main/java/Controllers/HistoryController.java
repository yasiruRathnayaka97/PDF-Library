package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Models.IndexManager;
import Models.WindowManager;

public class HistoryController implements Initializable {
    @FXML
    private StackPane spinner;
    @FXML
    private TableView<HistoryItem> historyTable;
    @FXML
    private TableColumn<HistoryItem,String> colKeyword;
    @FXML
    private TableColumn<HistoryItem,String> colType;
    @FXML
    private TableColumn<HistoryItem,String> colDirectory;
    @FXML
    private JFXButton btnClose;

    private HistoryManager historyManager;
    private AlertManager alertManager;
    private WindowManager windowManager;
    private SearchManager searchManager;
    private IndexManager indexManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyManager = HistoryManager.getInstance();
        alertManager = AlertManager.getInstance();
        windowManager = new WindowManager();
        searchManager = SearchManager.getInstance();
        indexManager = IndexManager.getInstance();
        spinner.setVisible(false);
        colKeyword.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("keyword"));
        colType.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("type"));
        colDirectory.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("directory"));

        historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
        historyTable.setPlaceholder(new Label("No history items"));

        colKeyword.prefWidthProperty().bind(historyTable.widthProperty().multiply(0.3));
        colType.prefWidthProperty().bind(historyTable.widthProperty().multiply(0.2));
        colDirectory.prefWidthProperty().bind(historyTable.widthProperty().multiply(0.5));
    }

    public void deleteHistoryFile(MouseEvent mouseEvent) {
        try {
            historyManager.deleteOne(historyTable.getSelectionModel().getSelectedItem().getId());
            historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
        }catch (Exception e){
            try {
                alertManager.showAlert("No item has been selected!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void clearHistoryFile(MouseEvent mouseEvent) {
        historyManager.deleteAll();
        historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
    }

    public void close(MouseEvent mouseEvent){
        windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
    }

    public void openHistoryFile(MouseEvent mouseEvent) throws IOException {
        try {
            indexManager.setDirPath(historyTable.getSelectionModel().getSelectedItem().getDirectory());
            Task longTask = new Task() {
                @Override
                protected Object call() throws Exception {
                    spinner.setVisible(true);
                    indexManager.indexDirectory();
                    return null;
                }
            };

            longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent t) {
                    try {
                        System.out.println(historyTable.getSelectionModel().getSelectedItem().getType().equals("content"));
                        spinner.setVisible(false);
                        System.out.println(historyTable.getSelectionModel().getSelectedItem().getType());
                        searchManager.setSearchResult(historyTable.getSelectionModel().getSelectedItem().getKeyword(),historyTable.getSelectionModel().getSelectedItem().getType());
                        windowManager.stageLoader("../ShowHistoryItem.fxml",2,"PDF-Library");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            new Thread(longTask).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
