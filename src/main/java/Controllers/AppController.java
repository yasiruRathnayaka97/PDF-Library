package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private JFXButton btnUser;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXListView listViewResult;
    @FXML
    private JFXComboBox dropDownSearchType;
    @FXML
    private StackPane spinner;

    private ObservableList<String> searchTypes;
    private IndexManager indexManager;
    private List<String> searchResult;
    private String keyword;
    private String searchType;
    private AlertManager alertManager;
    private HistoryManager historyManager;
    private FileManager fileManager;
    private FavoriteManager favoriteManager;
    private PdfManager pdfManager;
    private SearchManager searchManager;
    private StateManager stateManager;
    private WindowManager windowManager;
    private AccountManager accountManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();
        indexManager = IndexManager.getInstance();
        btnUser.textProperty().bind(accountManager.getUsername());

        //home pane
        alertManager = AlertManager.getInstance();
        historyManager = HistoryManager.getInstance();
        fileManager = new FileManager();
        searchTypes = FXCollections.observableArrayList("content","pdfName");
        dropDownSearchType.setItems(searchTypes);
        pdfManager = new PdfManager();
        favoriteManager = FavoriteManager.getInstance();
        searchManager = SearchManager.getInstance();
        stateManager=StateManager.getInstance();

        //close when sign out
        accountManager.getUsername().addListener((observableValue, oldValue, newValue) -> {
            if(newValue.equals("")){
                windowManager.closeWindow((Stage) btnUser.getScene().getWindow());
            }
        });

    }

    //----------------------------------------------------------------------------------------------------------------------------------
    //vertical box
    public void handleUser(MouseEvent mouseEvent) {
        try {
            windowManager.stageLoader("/User.fxml",3,"User Settings");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDirectory(MouseEvent mouseEvent) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        //get selected directory to file
        try{
            indexManager.setDirPath(dirChooser.showDialog((Stage) btnUser.getScene().getWindow()).getAbsolutePath());

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
                    spinner.setVisible(false);
                }
            });

            new Thread(longTask).start();
        }catch (Exception e){ }
    }

    public void handleFav(MouseEvent mouseEvent) {
        try {
            windowManager.stageLoader("/Favorite.fxml",2,"Favorites");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleHistory(MouseEvent mouseEvent) {
        try {
            windowManager.stageLoader("/History.fxml",2,"History");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------
    //Home Pane

    public void clickSearch(MouseEvent mouseEvent) {
        try {
            indexManager = IndexManager.getInstance();
            //check for empty search keywords
            if (textSearch.getText().equals("")) {
                alertManager.showAlert("Enter searching keyword!");
                return;
            }

            //check path is choose
            if (indexManager.getPaths() == null) {
                alertManager.showAlert("Select searching directory!");
                return;
            }

            //check for search Type has selected
            if (dropDownSearchType.getValue() == null) {
                alertManager.showAlert("Select search type directory!");
                return;
            }

            //call search manager
            searchType = dropDownSearchType.getValue().toString();
            keyword = textSearch.getText();
            searchManager.setSearchResult(keyword,searchType);
            //add to history
            historyManager.addHistory(keyword,searchType,indexManager.getDirPath());
            searchResult = searchManager.getSearchResult();

            //display search result in list view
            if (!searchResult.isEmpty()){
                listViewResult.setItems(FXCollections.observableArrayList(searchResult));
            }
            else{
                listViewResult.setItems(null);
                listViewResult.setPlaceholder(new Label(keyword+" keyword not match for any "+searchType));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFile(MouseEvent mouseEvent) {
        try {
            String[] arr=listViewResult.getSelectionModel().getSelectedItem().toString().split(":");
            pdfManager.openPdf(arr[arr.length-1].trim());
        }catch (Exception e){
            try {
                alertManager.showAlert("No item has been selected!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void addFav(MouseEvent mouseEvent) {
        try {
            String[] arr=listViewResult.getSelectionModel().getSelectedItem().toString().split(": ");
            favoriteManager.setPath(arr[arr.length-1].trim());
            favoriteManager.setKeyword(textSearch.getText());
            favoriteManager.setSearchType(dropDownSearchType.getSelectionModel().getSelectedItem().toString());
            windowManager.stageLoader("/NewFavorite.fxml",3,"Add favourite");
        } catch (Exception e) {
            try {
                alertManager.showAlert("No item has been selected!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveSearch(MouseEvent mouseEvent) {
        if(listViewResult.getItems().isEmpty()){
            try {
                alertManager.showAlert("No search results!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        windowManager.openSaveDialog((Stage) btnUser.getScene().getWindow());
    }
}
