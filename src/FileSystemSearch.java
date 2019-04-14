import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileSystemSearch {
    /**
     * инициализируем поиск по всей файловой системе
     * @param supportedProgList список поддерживаемых программ
     */
    public static File initiateSearch(SupportedProgList supportedProgList) throws IOException {
        LinkedList<String> pathlist = new LinkedList<>();
        initializeSearch(supportedProgList, pathlist);
        File paths = new File("./support");
        paths.mkdir();
        paths = new File("./support/paths.txt");
        paths.createNewFile();
        FileWriter writer = new FileWriter(paths);
        for (int i = 0; i < pathlist.size(); i++) {
            writer.write(pathlist.get(i) + "\n");
        }
        writer.close();
        return paths;
    }

    /**
     * начинаем сам поиск, запуская рекурсивный обход по файлам в каждом из корневых каталогов
     *
     * TODO узнать, можно ли как-то обходить каталоги с дровами, не зная их имен
     *
     * @param supportedProgList
     */
    private static void initializeSearch(SupportedProgList supportedProgList, LinkedList pathList){
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
    private static void searchUtil(File directory, SupportedProgList supportedProgList, LinkedList pathList ){
        Prog prog = supportedProgList.getProg(directory.getName());
        if(prog != null)
            prog.getInsideSearch(pathList, directory).start();

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
}
