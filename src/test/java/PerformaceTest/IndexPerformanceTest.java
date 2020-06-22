package PerformaceTest;

import Models.FileManager;
import Models.IndexManager;
import org.junit.Before;
import org.junit.Test;

public class IndexPerformanceTest {
    IndexManager indexManager;
    @Before
    public void setup(){
        this.indexManager = IndexManager.getInstance();
        FileManager fm=new FileManager();
        fm.createDir("./index_large");
        fm.createDir("./index_small");
    }
    @Test
    public void smallPDF(){
        //Performance access of indexing small PDF.
        String pdfPath= "./small.pdf";
        String dirPath= "./Index_small";
        long startTime= System.currentTimeMillis();
        this.indexManager.createIndex(pdfPath,dirPath);
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for small PDF indexing : "+execTime);
    }
    @Test
    public void largePDF(){
        //Performance access of indexing large PDF.
        String pdfPath= "./large.pdf";
        String dirPath= "./index_large";
        long startTime= System.currentTimeMillis();
        this.indexManager.createIndex(pdfPath,dirPath);
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for large PDF indexing : "+execTime);
    }
    @Test
    public void smallDirtStructure(){
        //without PDFs
        //Performance access of walking under dir.
        long startTime= System.currentTimeMillis();
        this.indexManager.setDirPath("./pdf_small");
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for walk under 1 deep 1 wide dir : "+execTime);
    }

    @Test
    public void largeDirStructure(){
        //without PDFs
        //Performance access of walking under dir.
        long startTime= System.currentTimeMillis();
        this.indexManager.setDirPath("./pdf_large");
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for walk under 5 deep 5 wide dir : "+execTime);
    }

    @Test
    public  void heavyWorkLoad(){
        //with pdf and dir structure.
        long  startTime= System.currentTimeMillis();
        this.indexManager.setDirPath("./pdf_heavy");
        this.indexManager.indexDirectory();
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for heavy workload : "+execTime);

    }
    @Test
    public  void liteWorkLoad(){
        //with pdf and dir structure.
        long startTime= System.currentTimeMillis();
        this.indexManager.setDirPath("./pdf_lite");
        this.indexManager.indexDirectory();
        long stopTime=System.currentTimeMillis();
        long execTime=stopTime-startTime;
        System.out.println("execution time for lite workload : "+execTime);

    }
}
