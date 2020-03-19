package Models;

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
import java.util.List;


public class IndexManager {
    PdfManager pm;
    Analyzer analyzer;
    public IndexManager() {
        this.analyzer= new StandardAnalyzer();
        this.pm = new PdfManager();
    }
    public String createIndex(String filePath,String  dirPath) {
        File file=new File(filePath);
        File dir=new File(dirPath);
        if (!file.exists()|!dir.exists()) {
            return "File or dir not exist";
        }
        Document document = new Document();
        String content=pm.readPdf(filePath);

        try {
            Directory directory = FSDirectory.open(Paths.get(dirPath));
            IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
            IndexWriter iw = new IndexWriter(directory, config);
            //index file contents
            Field contentField = new TextField("content",content,Field.Store.NO);
            String f=Paths.get(filePath).getFileName().toString();
            //index file name
            String pdfName=f.substring(0,f.length()-4);
            Field fileNameField = new TextField("pdfName",pdfName, Field.Store.YES);

            //index file path
            Field filePathField = new StringField("path", Paths.get(filePath).toString(), Field.Store.YES);
            document.add(contentField);
            document.add(fileNameField);
            document.add(filePathField);

            iw.addDocument(document);
            iw.close();
            return "Successfully Indexed";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Error" ;
        }

    }
public String indexDirectory(List<String> directoryPDFList, String dir){
        for(int i=0;i<directoryPDFList.size()-1;i++){
            createIndex(directoryPDFList.get(i),dir);
        }
        return "Successfully indexed directory";
}

}
//can use lucene ram directory for argument dirPath.
// TODO select best way.
