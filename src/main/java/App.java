import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Parent root;
    private Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("App.fxml"));
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PDF-Library");
        primaryStage.show();
    }
}



