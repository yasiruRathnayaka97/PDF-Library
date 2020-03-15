package Models;

import java.util.HashMap;

public class HistoryManager extends DBManager {

    public HistoryManager(){
        super("History");
    }

public String insertHistory(String pdfName,String path){
    HashMap<String,String> historyDet=new HashMap();
    historyDet.put("pdfName",pdfName);
    historyDet.put("path",path);
    insert(historyDet);
    return "successful";
}
}
