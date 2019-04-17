import insideChek.InsideChek;

import java.io.File;
import java.util.LinkedList;

public class Prog{
    private String Name;
    private String path;
    private InsideChek insideChek  = new InsideChek();

    public  Prog(String name){
        Name = name;
    }

    public InsideChek getInsideSearch(LinkedList pathList, File path) {
        insideChek.setPathList(pathList, path);
        return insideChek;
    }

    String getName(){return Name;}
}
