import insideChek.InsideChek;

import java.io.File;
import java.util.LinkedList;

public class Prog{
    private String Name;
    private String path;
    private InsideChek insideChek;

    public  Prog(String name){
        Name = name;
    }

    public InsideChek getInsideSearch(LinkedList paths, File path) {
        insideChek.setPathList(paths, path);
        return insideChek;
    }

    String getName(){return Name;}
}
