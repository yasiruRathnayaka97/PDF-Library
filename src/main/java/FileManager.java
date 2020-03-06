import java.io.File;

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
    }

