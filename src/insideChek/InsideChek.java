package insideChek;

import java.io.File;
import java.util.LinkedList;

public abstract class InsideChek extends Thread{
    private LinkedList pathList;
    File initRoot;
    public void setPathList(LinkedList pathList, String initRoot){
        this.pathList = pathList;
        this.initRoot = new File(initRoot);
    }
    @Override
    public void run(){
        makeSearch(pathList, initRoot);
    }

    public abstract boolean makeSearch(LinkedList<String> paths,File  initRoot);
}