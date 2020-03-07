import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;

public class searchManager {

    public String search(Directory dir, String fieldName, StandardAnalyzer analyzer, String  text) {

        try {
            DirectoryReader ir = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(ir);
            QueryParser parser = new QueryParser(fieldName, analyzer);
            Query query = parser.parse(text);
            ScoreDoc[] hits = is.search(query, 10).scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = is.doc(hits[i].doc);
                hitDoc.get(fieldName);
            }
            ir.close();
            dir.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
