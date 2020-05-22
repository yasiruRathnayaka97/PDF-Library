package Models;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    public boolean saveFile(String path){
        AccountManager accountManager = AccountManager.getInstance();
        SearchManager searchManager = SearchManager.getInstance();
        String resultout="";

        ArrayList<String> results = searchManager.getSearchResult();

        for (int i=0;i<results.size();i++){
            resultout+=results.get(i)+"\n\n";
        }

        String content = "username: "+accountManager.getUsername()
                +"\nkeyword: "+ searchManager.getSearchKeyword()
                + "\nsearch type: "+ searchManager.getSearchType()
                + "\nsearch results: \n"+ resultout;
        System.out.println(content);

        try {
            FileWriter fileWriter=new FileWriter(path);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String createDir(String path){
        File file4= new File(path);
        if (!file4.exists()) {
            boolean bool = file4.mkdir();
            if (bool==true){
                return "Dir created successfully";
            }
            else{
                return "Error";
            }
        }
        else {
            return "Dir almost exist";
        }
    }

    public List<String> getAllPDFUnderDir(String dir){
        try {
            Stream<Path> walk = Files.walk(Paths.get(dir));
            List<String> result= walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".pdf")).collect(Collectors.toList());
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }

    }
    //can use for delete directory.
    public  String clearDir(String path) {
        try {
            FileUtils.cleanDirectory(new File(path));
            return  "deleted";
        }
        catch(IOException e){
            e.printStackTrace();
            return "error";
        }

    }

}

