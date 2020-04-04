package Models;

public class AlertManager {
    private static AlertManager instance=new AlertManager();

    private AlertManager(){
        windowManager = new WindowManager();
    };

    public static AlertManager getInstance(){
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
