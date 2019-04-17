package insideChek;

import insideChek.searchRealisation.GetSearchRealisation;
import insideChek.searchRealisation.I_TypeOfSearch;

import java.io.File;
import java.util.LinkedList;

public class InsideChek extends Thread{
    private LinkedList pathList;
    private File initRoot;
    private I_TypeOfSearch searchMethod;

    public InsideChek(){
        pathList = null;
        initRoot = null;
        searchMethod = null;
    }

    public void setPathList(LinkedList pathList, File initRoot){
        this.pathList = pathList;
        this.initRoot = initRoot;
        this.searchMethod = GetSearchRealisation.getSearch(initRoot.getName());
    }

    @Override
    public void run(){ searchMethod.search(pathList, initRoot);
    }
}