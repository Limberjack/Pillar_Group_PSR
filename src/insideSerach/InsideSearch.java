package insideSerach;

import java.io.File;
import java.util.LinkedList;

public class InsideSearch extends Thread{
    private boolean makeSearch(){
        return false;
    }
    private LinkedList pathList;
    public InsideSearch( LinkedList pathList){
        this.pathList = pathList;
    }
    @Override
    public void run(){
        makeSearch();
    }
}