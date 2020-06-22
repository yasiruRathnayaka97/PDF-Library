package Models;

import org.apache.commons.io.FileUtils;

import java.io.*;
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
        IndexManager indexManager = IndexManager.getInstance();
        String resultout="";

        ArrayList<String> results = searchManager.getSearchResult();

        for (int i=0;i<results.size();i++){
            resultout+=results.get(i)+"\n\n";
        }

        String content = "username: "+accountManager.getUsername()
                +"\n\nkeyword: "+ searchManager.getSearchKeyword()
                + "\n\nsearch type: "+ searchManager.getSearchType()
                +"\n\nsearch directory: "+ indexManager.getDirPath()
                + "\n\nsearch results: \n"+ resultout;
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
    public boolean isParentAndChild(String parent,String child){
        Path parentPath =Paths.get(parent).toAbsolutePath();
        Path childPath = Paths.get(child).toAbsolutePath();
        if (childPath.startsWith(parentPath))
            return true;
        else
            return  false;

    }

    public String writeSubIndexDirInfo(String indexDir,String superParentPath,int weight,List <String> pathList) {

        String content = "";
        for (int j = 0; j < pathList.size(); j++) {
            content += pathList.get(j);
        }
        content = indexDir +","+superParentPath+","+weight +","+content+"\n";
        return content;
    }
        public boolean writeIndexDirInfo(ArrayList<State> stateList){
            String path = "./indexInfo";
            String out="";
            for (int i=0;i<stateList.size(); i++){
                out+=this.writeSubIndexDirInfo(stateList.get(i).getIndexDir(),stateList.get(i).getSuperParentPath(),stateList.get(i).getWeight(),stateList.get(i).getPathList());
            }
            try {
                FileWriter fileWriter = new FileWriter(path);
                fileWriter.write(out);
                fileWriter.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        public ArrayList<State> readIndexDirInfo(ArrayList<State> stateList) throws IOException {

            try(BufferedReader br=new BufferedReader(new FileReader("./IndexInfo"))){
                String line;
                while((line=br.readLine())!=null){
                    String[] arr=line.split(",");
                    State state=new State();
                    state.setIndexDir(arr[0]);
                    state.setSuperParentPath(arr[1]);
                    state.setWeight(Integer.parseInt(arr[2]));
                    ArrayList<String> list=new ArrayList<String>();
                    for(int k=3;k<arr.length;k++){
                        list.add(arr[k]);
                    }
                    state.setPathList(list);
                    stateList.add(state);

                }
                return stateList;
            }
            catch(FileNotFoundException e){
                return stateList;
            }
        }
    }






