package Models;

import javafx.beans.property.SimpleStringProperty;
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
import java.util.ArrayList;
import java.util.List;

public class IndexManager {
    PdfManager pm;
    Analyzer analyzer;
    private List<String> paths;
    private String dirPath;
    private SimpleStringProperty appTitle;
    private FileManager fm;
    private static IndexManager instance;
    private PdfCrawler pc;
    private  StateManager stateManager;
    private IndexManager() {
        this.analyzer= new StandardAnalyzer();
        this.pm = new PdfManager();
        this.fm = new FileManager();
        this.pc= new PdfCrawler();
        this.appTitle = new SimpleStringProperty("PDF Library");
        this.stateManager=StateManager.getInstance();

    }

    public static IndexManager getInstance(){
        if (instance == null)
            instance = new IndexManager();
        return instance;
    }

    public String createIndex(String filePath,String indexDirPath) {
        this.fm.createDir(indexDirPath);
        File file = new File(filePath);
        File dir = new File(indexDirPath);

        if (!file.exists() | !dir.exists()) {

            return "File or dir not exist";
        }
        //index file name
        String f = Paths.get(filePath).getFileName().toString();
        String pdfName = f.substring(0, f.length() - 4);
        Field fileNameField = new TextField("pdfName", pdfName, Field.Store.YES);

        //index file path
        Field filePathField = new StringField("path", Paths.get(filePath).toString(), Field.Store.YES);
        ArrayList<String> contentArrayList = pm.readPdf(filePath);
        ArrayList<String[]> docArr = pc.getCrawlData(contentArrayList);
          try {
                int weight=0;
                Directory directory = FSDirectory.open(Paths.get(indexDirPath));
                IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
                IndexWriter iw = new IndexWriter(directory, config);
                for (String[] doc:docArr) {
                    weight++;
                    Document document = new Document();
                    String content=doc[1];
                    String pageNumber=doc[0];
                    //index file contents
                     Field contentField = new TextField("content", content, Field.Store.YES);
                    //index file pageNumber
                    Field pageNumberField = new TextField("pageNum",pageNumber, Field.Store.YES);
                    document.add(contentField);
                    document.add(fileNameField);
                    document.add(filePathField);
                    document.add(pageNumberField);
                    iw.addDocument(document);

                }
                iw.close();
               stateManager.getCurrentState().setWeight(stateManager.getCurrentState().getWeight()+weight);
               return "Successfully Indexed";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }


    }

    public String indexDirectory(){
//       this.fm.clearDir("./Index");
        if (paths==null){
            return "No paths";
        }

        for(int i=0;i<this.paths.size();i++){
            this.createIndex(this.paths.get(i), stateManager.getCurrentState().getIndexDir());
        }
        return "Successfully indexed directory";
    }

    public List<String> getPaths() {
        return paths;
    }

    public String setDirPath(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return "dir not exist";
        }
        this.dirPath = dirPath;
        this.paths = stateManager.updateStates(dirPath,fm.getAllPDFUnderDir(dirPath));
        this.appTitle.set("PDF Library ["+ dirPath+"]");
        System.out.println("Paths updated");
        return "Paths updated";
    }

    public String getDirPath() {
        return dirPath;
    }

    public SimpleStringProperty appTitleProperty() {
        return appTitle;
    }
}
//can use lucene ram directory for argument dirPath.
// TODO select best way.
