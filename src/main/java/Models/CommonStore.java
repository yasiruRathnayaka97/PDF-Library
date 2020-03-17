package Models;

public class CommonStore {
    private static CommonStore single_instance = null;

    private String message;

    private CommonStore(){};

    public static CommonStore getInstance(){
        if (single_instance == null)
            single_instance = new CommonStore();
        return single_instance;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
