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

import java.io.File;
import java.nio.file.Paths;


public class IndexManager {
    PdfManager pm;
    public IndexManager() {
        this.pm = new PdfManager();
    }
    public String createIndex(String filePath,String  dirPath,String collectionName) {
        File file=new File(filePath);
        File dir=new File(dirPath);
        if (!file.exists()|!dir.exists()) {
            return "File or dir not exist";
        }
        Analyzer analyzer = new StandardAnalyzer();
        Document document = new Document();
        String content=pm.readPdf(filePath);

        try {
            Directory directory = FSDirectory.open(Paths.get(dirPath));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iw = new IndexWriter(directory, config);
            //index file contents
            Field contentField = new TextField("content",content,Field.Store.NO);

            //index file name
            Field fileNameField = new StringField("fileName",Paths.get(filePath).getFileName().toString(), Field.Store.YES);

            //index file path
            Field filePathField = new StringField("path", Paths.get(filePath).toString(), Field.Store.YES);
            //index file collectionName
            document.add(contentField);
            document.add(fileNameField);
            document.add(filePathField);
            iw.addDocument(document);
            iw.close();
            return "Successfully Indexed";
        }
        catch (Exception e){
            return "Error" ;
        }

    }
public String indexDirectory(){
        return "";
}

public String search(){

        return "";
}
}
