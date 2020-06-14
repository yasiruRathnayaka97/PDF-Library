package Models;

import java.util.ArrayList;
import java.util.List;

public class StateManager {

    private static  ArrayList<State> stateList;
    private static StateManager instance;
    private static  FileManager fm;
    private static  State currentState;
    private StateManager(){
       fm=new FileManager();



    }
    public static StateManager getInstance(){
        if (instance == null)
            instance = new StateManager();
        return instance;
    }
    public void setCurrentState(State currentState){
        this.currentState=currentState;
    }
    public  State getCurrentState(){
        return this.currentState;
    }
    public void setStateList(ArrayList<State> stateList){
        this.stateList=stateList;
    }
    public ArrayList<State> getStateList(){
        return this.stateList;
    }
    public State updateStates(String parentPath,List<String> pathList,int weight ){
        for (int i=0;i<stateList.size(); i++){
             if(fm.isParentAndChild(parentPath,stateList.get(i).getSuperParentPath())){
                //In the case of new directory selected is a parent of previously searched directory.
                List<String> selectedPathList=stateList.get(i).getPathList();
                ArrayList<String> newPathList=new ArrayList<String>();
                for (int j=0;j<pathList.size(); j++){
                    if(!selectedPathList.contains(pathList.get(j))) {
                        newPathList.add(pathList.get(j));
                    }
                }
                stateList.get(i).setPathList(newPathList);
                this.setCurrentState(stateList.get(i));
                return stateList.get(i);
            }
             else if (fm.isParentAndChild(stateList.get(i).getSuperParentPath(),parentPath)){
                 //In the case of new directory selected is a child of previously searched directory.
                State newState=new State();
                newState.setPathList(pathList);
                newState.setSuperParentPath(parentPath);
                newState.setWeight(weight);
                newState.setIndexDir("./index/index_"+stateList.size());
                stateList.add(newState);
                this.setCurrentState(newState);
                return newState;

            }


            else {
                 //In the case of no directory relation.
                State newState=new State();
                newState.setPathList(pathList);
                newState.setSuperParentPath(parentPath);
                newState.setWeight(weight);
                newState.setIndexDir("./index/index_"+stateList.size());
                stateList.add(newState);
                this.setCurrentState(newState);
                return newState;
            }
        }
        //In the case of sateList empty.
        State newState=new State();
        newState.setPathList(pathList);
        newState.setSuperParentPath(parentPath);
        newState.setWeight(weight);
        newState.setIndexDir("./index/index_"+stateList.size());
        stateList.add(newState);
        this.setCurrentState(newState);
        return newState;
    }

}
