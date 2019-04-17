import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class FileSystemSearch {
    /**
     * инициализируем поиск по всей файловой системе
     * @param supportedProgList список поддерживаемых программ
     */
    public static void initiateSearch(LinkedList<Prog> supportedProgList, LinkedList<String> pathList) throws IOException {

        initializeSearch(supportedProgList, pathList);
        /*File paths = new File("./support");
        paths.mkdir();
        paths = new File("./support/paths.txt");
        paths.createNewFile();*/

    }

    /**
     * начинаем сам поиск, запуская рекурсивный обход по файлам в каждом из корневых каталогов
     *
     * TODO узнать, можно ли как-то обходить каталоги с дровами, не зная их имен
     *
     * @param supportedProgList
     */
    private static void initializeSearch(LinkedList<Prog> supportedProgList, LinkedList pathList){
        Scanner fileRead = new Scanner("SupportedPrograms");
        File[] roots = File.listRoots();

        while(fileRead.hasNext())
           supportedProgList.add(new Prog(fileRead.nextLine()));
        for (int i = 0; i < roots.length; i++) {
            searchUtil(roots[i], supportedProgList, pathList);
        }

    }

    /**
     * если в процессе обхода мы встречаем папку с именем, как поддерживаемая программа, то запускаем еще один поток,
     * задача которого убедиться в том, что мы нашли именно программу, а не просто папку с таким же названием
     *
     * @param directory
     */
    private static void searchUtil(File directory, LinkedList<Prog> supportedProgList, LinkedList pathList ){

        if(contains(supportedProgList, directory)) {
            new Prog(directory.getName()).getInsideSearch(pathList, directory).start();
           // System.out.println(directory.getName());
        }


        if(directory.canRead()) {
            File[] allFilesIn = directory.listFiles();
            for (int i = 0; i < allFilesIn.length; i++) {
                if (allFilesIn[i].isDirectory() &&
                        !(allFilesIn[i].getAbsolutePath().equals("/proc") ||
                                allFilesIn[i].getAbsolutePath().equals("/sys")) //TODO убери костыль
                )
                    searchUtil(allFilesIn[i], supportedProgList, pathList);
            }
        }
    }


    private static boolean contains(LinkedList<Prog> supportedProgList, File directory){
        Iterator<Prog> i = supportedProgList.descendingIterator();
        while(i.hasNext())
            if(i.next().getName().equals(directory.getName()))
                return true;

        return false;
    }
}
