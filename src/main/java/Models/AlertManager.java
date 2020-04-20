package Models;

public class AlertManager {
    private static AlertManager instance;

    private AlertManager(){
        windowManager = new WindowManager();
    }

    public static AlertManager getInstance(){
        if (instance == null)
            instance = new AlertManager();
        return instance;
    }

    private WindowManager windowManager;
    private String message;

    public String getMessage() {
        return message;
    }

    public void showAlert(String message) throws Exception{
        //set message
        this.message = message;

        //load alert stage
        windowManager.stageLoader("../AlertWindow.fxml",true,null);
    }
}
