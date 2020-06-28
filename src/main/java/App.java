
import Models.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;


public class App extends Application {
    private WindowManager windowManager;
    private AccountManager accountManager;
    public static void main(String[] args)
    {
        FileManager fm=new FileManager();
        StateManager sm=StateManager.getInstance();
        AccountManager accountManager = AccountManager.getInstance();
        fm.createDir("./index");
        ArrayList<State> stateList=new ArrayList<State>();
        try {
            sm.setStateList(fm.readIndexDirInfo(stateList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();

        accountManager.readLastLogged();

        if(accountManager.checkLoggingCorrect(accountManager.getUsername().getValue(),accountManager.getPassword().getValue())){
            windowManager.stageLoader("/App.fxml",1,"PDF Library");
        }
        else{
            //load sign in sign up stage
            windowManager.stageLoader("/SignInSignUp.fxml",3,"Welcome to PDF Library");
        }
    }
}



