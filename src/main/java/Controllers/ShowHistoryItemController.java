package Controllers;

import Models.SearchManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ShowHistoryItemController implements Initializable {
    @FXML
    private Label lblKeyword;
    @FXML
    private Label lblType;
    @FXML
    private JFXListView listViewResult;

    private SearchManager searchManager;
    private WindowManager windowManager;
    private List<String> searchResult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchManager = SearchManager.getInstance();
        windowManager = new WindowManager();
        lblKeyword.setText(searchManager.getSearchKeyword());
        lblType.setText(searchManager.getSearchType());
        searchResult = searchManager.getSearchResult();
        //display search result in list view
        if (!searchResult.isEmpty()){
            listViewResult.setItems(FXCollections.observableArrayList(searchResult));
        }
        else{
            listViewResult.setPlaceholder(new Label(searchManager.getSearchKeyword()+" not match for any "+searchManager.getSearchType()));
        }

    }

    public void close(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) lblKeyword.getScene().getWindow());
    }
}
