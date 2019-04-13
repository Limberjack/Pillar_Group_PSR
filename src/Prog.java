import insideSerach.InsideSearch;

public class Prog{
    private String Name;
    private Class insideSearchType;
    private String path;
    private InsideSearch insideSearch;

    /**
     *
     * @param name имя программы
     * @param path путь до директории с программой
     * @param currentSearch тип поиска, который нужно применять для данной программы
     */
    public  Prog(String name, String path, InsideSearch currentSearch){
        Name = name;
        this.path = path;
        insideSearch = currentSearch;
    }

    public InsideSearch getInsideSearch(String path) {
        return insideSearch;
    }

    String getName(){return Name;}
}
