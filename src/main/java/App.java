import Models.CommonStore;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    private Parent root;
    private Scene scene;

    private double xOffset = 0;
    private double yOffset = 0;

    private CommonStore commonStore;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        commonStore = CommonStore.getInstance();

        //load sign in sign up stage
        commonStore.stageLoader("../SignInSignUp.fxml",true,null);
    }
}



