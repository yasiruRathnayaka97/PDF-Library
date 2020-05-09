package Models;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    public void saveFile(String content){

        SearchManager searchManager = SearchManager.getInstance();
        System.out.println(searchManager);

        /*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Search");
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter(".txt files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try {
                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.println(content);
                writer.close();
            } catch (IOException ex) {

            }
        }*/
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
        }

    catch(IOException e)

    {
        e.printStackTrace();
    }
    return  "deleted";
    }

    }

