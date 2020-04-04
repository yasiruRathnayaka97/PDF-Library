package Models;

import java.util.List;

public class CommonStore {
    private static CommonStore single_instance = null;

    private String username;
    private List<String> files;
    private IndexManager indexManager;
    private WindowManager windowManager;

    private CommonStore(){
        indexManager = new IndexManager();
        windowManager = new WindowManager();
    };

    public static CommonStore getInstance(){
        if (single_instance == null)
            single_instance = new CommonStore();
        return single_instance;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getFiles() {
        return files;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void indexDirectory(List<String> directoryPDFList, String dir){
        indexManager.indexDirectory(directoryPDFList, dir);
    }

    public IndexManager getIndexManager() {
        return indexManager;
    }

    public void loadingStage(String process) throws Exception {
        windowManager.stageLoader("../IndexingLoader.fxml",true,null);
    }
}
