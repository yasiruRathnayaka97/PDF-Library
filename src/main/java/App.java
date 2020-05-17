
import Models.AccountManager;
import Models.FileManager;
import Models.WindowManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.channels.AcceptPendingException;


public class App extends Application {
    private WindowManager windowManager;
    private AccountManager accountManager;

    public static void main(String[] args)
    {
        FileManager fm=new FileManager();
        AccountManager accountManager = AccountManager.getInstance();
        fm.createDir("./index");
        launch();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();

        accountManager.readLastLogged();

        if(accountManager.checkLoggingCorrect(accountManager.getUsername().getValue(),accountManager.getPassword().getValue())){
            windowManager.stageLoader("../App.fxml",false,"PDF Library");
        }
        else{
            //load sign in sign up stage
            windowManager.stageLoader("../SignInSignUp.fxml",true,null);
        }
    }
}



