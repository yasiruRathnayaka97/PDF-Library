package Models;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    public String createDirUser(String userName){
        createDir(userName);
        createDir(userName+"/collections");
        createDir(userName+"index");
        return "User created Successfully";
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

    public String createCollection(String userName,String collectionName){
       createDir(userName+"/collections/"+collectionName);
       return "Collection created successfully";
    }

    public String copySrcToDest(String src,String dest) {
        try{
            Files.copy(Paths.get(src),Paths.get(dest));
            return "Successfully copied";
        }
        catch (IOException e){
            e.printStackTrace();
            return "Error";
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
        }

    catch(IOException e)

    {
        e.printStackTrace();
    }
    return  "deleted";
    }

    }

