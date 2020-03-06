import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Path;


public class IndexManager {

    public String createIndex(Path file,Path directoryPath,String collectionName) {
        Analyzer analyzer = new StandardAnalyzer();
        Document document = new Document();
        String content="";

        try {
            Directory directory = FSDirectory.open(directoryPath);
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            //index file contents
            Field contentField = new TextField("content",content,Field.Store.NO);

            //index file name
            Field fileNameField = new StringField("fileName",file.getFileName().toString(), Field.Store.YES);

            //index file path
            Field filePathField = new StringField("path", file.toString(), Field.Store.YES);
            //index file collectionName
            document.add(contentField);
            document.add(fileNameField);
            document.add(filePathField);
            iwriter.addDocument(document);
            iwriter.close();
            return "Successfully Indexed";
        }
        catch (Exception e){
            return "Error" ;
        }

    }
public String indexDirectory(){
        return "";
}

}
