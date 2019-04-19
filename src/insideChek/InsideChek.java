package insideChek;

import insideChek.searchRealisation.GetSearchRealisation;
import insideChek.searchRealisation.I_TypeOfSearch;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class InsideChek extends Thread{
    private LinkedList pathList;
    private File initRoot;
    private I_TypeOfSearch searchMethod;
    private LinkedHashSet<String> progNames;
    public InsideChek(){
        pathList = null;
        initRoot = null;
        searchMethod = null;
    }

    public void setPathList(LinkedList pathList, File initRoot, LinkedHashSet<String> progNames){
        this.pathList = pathList;
        this.initRoot = initRoot;
        this.searchMethod = GetSearchRealisation.getSearch(initRoot.getName());
        this.progNames = progNames;
    }

    @Override
    public void run(){
        if(searchMethod.search(pathList, initRoot))
            progNames.add(initRoot.getName());

    }
}