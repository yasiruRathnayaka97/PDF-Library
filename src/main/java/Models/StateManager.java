package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public List<String> updateStates(String parentChildPath,List<String> pathList){
        Collections.sort(stateList, Comparator.comparing(State::getPathListLength));
        for (int i=0;i<stateList.size(); i++){
             if(fm.isParentAndChild(parentChildPath,stateList.get(i).getSuperParentPath())){
                //In the case of new directory selected is a parent of previous searched directory.
                List<String> selectedPathList=stateList.get(i).getPathList();
                List<String> addPathList=new ArrayList<String>();
                List<String> removePathList=new ArrayList<String>();
                for (int j=0;j<pathList.size(); j++){
                    if(!selectedPathList.contains(pathList.get(j))) {
                        addPathList.add(pathList.get(j));
                    }

                }
                 for (int j=0;j<selectedPathList.size(); j++){
                     if(!pathList.contains(selectedPathList.get(j))) {
                         removePathList.add(pathList.get(j));
                     }

                 }
                stateList.get(i).setPathList(pathList);
                if(addPathList.isEmpty()==false){
                    stateList.get(i).setSuperParentPath(parentChildPath);
                }

                this.setCurrentState(stateList.get(i));
                if(removePathList.isEmpty()==true){
                    return addPathList;
                }
                else{
                    return pathList;
                }

            }
//             else if (fm.isParentAndChild(stateList.get(i).getSuperParentPath(),parentChildPath)){
//                 //In the case of new directory selected is a child of previously searched directory.
//                State newState=new State();
//                newState.setPathList(pathList);
//                newState.setSuperParentPath(parentChildPath);
//                newState.setWeight(weight);
//                newState.setIndexDir("./index/index_"+stateList.size());
//                stateList.add(newState);
//                this.setCurrentState(newState);
//                return pathList;
//
//            }


//            else {
//                 //In the case of no directory relation.
//                State newState=new State();
//                newState.setPathList(pathList);
//                newState.setSuperParentPath(parentChildPath);
//                newState.setWeight(weight);
//                newState.setIndexDir("./index/index_"+stateList.size());
//                stateList.add(newState);
//                this.setCurrentState(newState);
//                return pathList;
//            }
        }
        //In the case of sateList empty,In the case of no directory relation,In the case of new directory selected is a child of previously searched directory.
        int weight=0;
        State newState=new State();
        newState.setPathList(pathList);
        newState.setSuperParentPath(parentChildPath);
        newState.setWeight(weight);
        newState.setIndexDir("./index/index_"+stateList.size());
        stateList.add(newState);
        this.setCurrentState(newState);
        return pathList;
    }


}
