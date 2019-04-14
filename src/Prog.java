import insideChek.InsideChek;

import java.io.File;
import java.util.LinkedList;

public class Prog{
    private String Name;
    private Class insideSearchType;
    private String path;
    private InsideChek insideChek;

    /**
     *
     * @param name имя программы
     * @param path путь до директории с программой
     * @param currentSearch тип поиска, который нужно применять для данной программы
     */
    public  Prog(String name, String path, InsideChek currentSearch){
        Name = name;
        this.path = path;
        insideChek = currentSearch;
    }

    public  Prog(String name){
        Name = name;
    }

    public InsideChek getInsideSearch(LinkedList paths, File path) {
        insideChek.setPathList(paths, path.getAbsolutePath());
        return insideChek;
    }

    String getName(){return Name;}
}
