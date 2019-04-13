import insideSerach.InsideSearch;

public class Prog{
    private String Name;
    //private String Path;
    private InsideSearch insideSearch;
    public  Prog(String name/*, String path, InsideSearch currentSearch*/){
        Name = name;
        //Path = path;
        //insideSearch = currentSearch;
    }

    public InsideSearch getInsideSearch() {
        return insideSearch;
    }

    String getName(){return Name;}
}
