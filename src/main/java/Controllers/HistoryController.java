package Controllers;
import Models.AccountManager;
import Models.HistoryItem;
import Models.HistoryManager;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.*;

public class HistoryController implements Initializable{
    @FXML
    private TableView<HistoryItem> tableView;
    @FXML
    private TableColumn<HistoryItem,String> keyword;
    @FXML
    private TableColumn<HistoryItem,String> type;
    @FXML
    private TableColumn<HistoryItem,String> path;
    @FXML
    private JFXButton btnOpen;
    @FXML
    private JFXButton btnRemove;

    private AccountManager account;
    private HistoryManager historyManager;
    private ObservableList history;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account=AccountManager.getInstance();
        historyManager = HistoryManager.getInstance();
        history = FXCollections.observableArrayList(historyManager.getHistory());
        keyword.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("keyword"));
        type.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("type"));
        path.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("directory"));
        tableView.setItems(history);
    }

    public void clickOpen(MouseEvent mouseEvent) {
        System.out.println(tableView.getSelectionModel().getSelectedItem().getId());
    }

    public void clickRemove(MouseEvent mouseEvent) {
        history.remove(tableView.getSelectionModel().getSelectedIndex());
        //historyManager.deleteOne(tableView.getSelectionModel().getSelectedItem().getId(),tableView.getSelectionModel().getSelectedIndex());
    }
}