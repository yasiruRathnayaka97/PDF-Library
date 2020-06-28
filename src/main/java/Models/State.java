package Models;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<String> pathList;
    private int weight;
    private String superParentPath;
    private String indexDir;

    public List<String> getPathList() {
        return pathList;
    }

    public void setIndexDir(String indexDir) {
        this.indexDir = indexDir;
    }

    public String getIndexDir() {
        return indexDir;
    }

    public int getWeight() {
        return weight;
    }

    public String getSuperParentPath() {
        return superParentPath;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSuperParentPath(String superParentPath) {
        this.superParentPath = superParentPath;
    }
    public int getPathListLength() {
        return pathList.size();
    }
}
