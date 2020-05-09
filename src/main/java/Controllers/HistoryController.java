package Controllers;

import Models.AlertManager;
import Models.HistoryItem;
import Models.HistoryManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableView<HistoryItem> historyTable;
    @FXML
    private TableColumn<HistoryItem,String> colKeyword;
    @FXML
    private TableColumn<HistoryItem,String> colType;
    @FXML
    private TableColumn<HistoryItem,String> colDirectory;

    private HistoryManager historyManager;
    private AlertManager alertManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyManager = HistoryManager.getInstance();
        alertManager = AlertManager.getInstance();

        colKeyword.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("keyword"));
        colType.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("type"));
        colDirectory.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("directory"));

        historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));

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
}
