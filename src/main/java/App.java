import Models.WindowManager;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class App extends Application {
    private WindowManager windowManager;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        windowManager = new WindowManager();

        //load sign in sign up stage
        windowManager.stageLoader("../SignInSignUp.fxml",true,null);
    }
}



