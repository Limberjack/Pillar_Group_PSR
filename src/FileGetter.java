import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class FileGetter extends  Thread{
    /**
     * 1)создаем список поддерживаемых программ
     * 2)запускаем полный обход по файловой системе в поисках этих программ
     * 3)спрашиваем пользователя, какие из найденых программ он хочет сохранить
     *
     * @param
     * @throws FileNotFoundException
     */
    public static String[] getPathsArray() {
        LinkedList<Prog> supportedProgList = new LinkedList<>();
        LinkedList<String> pathList = new LinkedList<String>();


        try {                                                                                        // создаем связный список имен поддерживаемых программ
            supportedProgList = DependMaker.buildDependence(new File("./SupportedPrograms.txt"));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("нет файла, содержащего список поддерживаемых программ");
        }

        try {
            FileSystemSearch.initiateSearch(supportedProgList, pathList);                 //начинаем поиск
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toArray(pathList);
    }
    private static String[] toArray(LinkedList<String> s){
        String a[] = new String[s.size()];
        Iterator<String> i= s.iterator();
        for (int j = 0; i.hasNext(); j++) {
            a[j] = i.next();
        }

        return a;
    }

    public static void main(String[] args) {
        String[] s = getPathsArray();
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}

