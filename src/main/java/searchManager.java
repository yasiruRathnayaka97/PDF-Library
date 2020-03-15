
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SearchManager {
   IndexManager im;
    public SearchManager(IndexManager im){
        this.im=im;
    }

    public ArrayList search(String dirPath, String fieldName, String  text) {
        ArrayList<String> matchPdfList=new ArrayList<String>();
        try {
            Directory dir = FSDirectory.open(Paths.get(dirPath));
            DirectoryReader ir = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(ir);
            QueryParser parser = new QueryParser(fieldName, im.analyzer);
            Query query = parser.parse(text);
            ScoreDoc[] hits = is.search(query, 10).scoreDocs;
            for(int i=0;i<hits.length;++i) {
                int docId = hits[i].doc;
                Document d = is.doc(docId);
                matchPdfList.add(d.get("path"));

            }

            ir.close();
            dir.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return matchPdfList ;
    }

}
