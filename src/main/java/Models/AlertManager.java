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
    private String actionName;

    public String getMessage() {
        return message;
    }

    public String getActionName() {
        return actionName;
    }

    public void showAlert(String message) throws Exception{
        //set message
        this.message = message;

        //load alert stage
        windowManager.stageLoader("../AlertWindow.fxml",3,"Alert!");
    }

    public void showConfirmWindow(String message,String actionName){
        //set message
        this.message = message;

        //set action name
        this.actionName = actionName;

        //load alert stage
        try {
            windowManager.stageLoader("../ConfirmWindow.fxml",3,"Confirm!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
