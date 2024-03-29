package Models;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.FSDirectory;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchManager {

    private static SearchManager instance;
    private IndexManager indexManager;
    private HistoryManager historyManager;
    private StateManager stateManager;
    private SearchManager(){
        indexManager = IndexManager.getInstance();
        stateManager=stateManager.getInstance();
        historyManager = HistoryManager.getInstance();
    };

    public static SearchManager getInstance(){
        if(instance == null)
            instance = new SearchManager();
        return instance;
    }



    private ArrayList<String> searchResult;
    private String searchType;
    private String searchKeyword;

    public String setSearchResult(String searchKeyword, String searchType) {
        searchResult = search(searchType,searchKeyword);
        this.searchType = searchType;
        this.searchKeyword = searchKeyword;
        return "success";
    }

    public ArrayList<String> getSearchResult() {
        return searchResult;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public ArrayList<String> search(String searchType, String  keyword) {
        String dirPath=stateManager.getCurrentState().getIndexDir();
        ArrayList<String> matchPdfList=new ArrayList<String>();
        try {
            Directory dir = FSDirectory.open(Paths.get(dirPath));
            DirectoryReader ir = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(ir);
            QueryParser parser = new QueryParser(searchType, indexManager.analyzer);
            Query query = parser.parse(keyword);
            ScoreDoc[] hits = is.search(query, 100).scoreDocs;
            double sum=0;
            double val=0;
            for (int j=0;j<hits.length;++j){
                sum+=hits[j].score;
            }
            for(int i=0;i<hits.length;++i) {
                if (hits[i].score/sum>=val*0.75){
                    val=hits[i].score/sum;
                    int docId = hits[i].doc;
                    Document d = is.doc(docId);
                    String resultPath = d.get("path");
                    String resultContent = d.get("content");
                    String resultPdfName = d.get("pdfName");
                    String resultPageNum = d.get("pageNum");
                if (searchType.equals("content")) {

                    String result1 = "Match content: " + resultContent + ".\n" + "Match PDF name: " + resultPdfName + "\n" + "Match page number: " + resultPageNum + "\n" + "Match PDF path: " + resultPath;
                    matchPdfList.add(result1);

                } else {
                    String result2 = "Match PDF name: " + resultPdfName + "\n" + "Match PDF path: " + resultPath;
                    matchPdfList.add(result2);
                }
            }
            }
            ir.close();
            dir.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if(searchType=="pdfName"){
            ArrayList<String> newMatchPdfList=new ArrayList<String>();
            for(String pdf:matchPdfList){
                if(!newMatchPdfList.contains(pdf)){
                    newMatchPdfList.add(pdf);
                }
            }
            return  newMatchPdfList;
        }
        else{
            return matchPdfList ;
        }

    }

}
