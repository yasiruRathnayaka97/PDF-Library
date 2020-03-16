package Models;

import java.util.HashMap;

public class HistoryManager extends DBManager {

    public HistoryManager() {
        super("History");
    }

//    public String insertHistorySearchKeyword(String keyword, String dir) {
//        HashMap<String, String> historyDetSK = new HashMap();
//        historyDetSK.put("keyword", keyword);
//        historyDetSK.put("dirName", dir);
//        insert(historyDetSK);
//        return "successful";
//    }

    public String insertHistoryOpenedPDF(String pdfName, String path) {
        HashMap<String, String> historyDetO = new HashMap();
        historyDetO.put("pdfName", pdfName);
        historyDetO.put("path", path);
        insert(historyDetO);
        return "successful";
    }
    //can use relevant method according to need.But can not use both.
    // If search keywords add to the history use insertHistorySearchKeyword().
    //else Paths of opened file add to the history use insertHistoryOpenedPDF().
}
