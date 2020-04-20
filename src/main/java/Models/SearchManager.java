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

public class SearchManager {
    private IndexManager indexManager;
    private HistoryManager historyManager;


    public SearchManager(){
        indexManager = IndexManager.getInstance();
        historyManager = HistoryManager.getInstance();
    }

    public ArrayList search(String dirPath, String searchType, String  keyword) {
        ArrayList<String> matchPdfList=new ArrayList<String>();
        try {
            Directory dir = FSDirectory.open(Paths.get(dirPath));
            DirectoryReader ir = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(ir);
            QueryParser parser = new QueryParser(searchType, indexManager.analyzer);
            Query query = parser.parse(keyword);
            ScoreDoc[] hits = is.search(query, 10).scoreDocs;
            for(int i=0;i<hits.length;++i) {
                int docId = hits[i].doc;
                Document d = is.doc(docId);
                matchPdfList.add(d.get("path"));
            }
            ir.close();
            dir.close();

            //add to history
            historyManager.addHistory(keyword,searchType,indexManager.getDirPath());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return matchPdfList ;
    }

}
