import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class FileSystemSearch {
    /**
     * инициализируем поиск по всей файловой системе
     * @param supportedProgList список поддерживаемых программ
     */
    public static void initiateSearch(SupportedProgList supportedProgList){
        initializeSearch(supportedProgList);
    }

    /**
     * начинаем сам поиск, запуская рекурсивный обход по файлам из корневого каталога
     *
     * беда. он идет только из С диска. ни о каких других дисках он не подозревает
     *
     * @param supportedProgList
     */
    private static void initializeSearch(SupportedProgList supportedProgList){
        Scanner fileRead = new Scanner("SupportedPrograms");
        while(fileRead.hasNext())
            supportedProgList.add(new Prog(fileRead.nextLine()));
        searchUtil(new File("/"));
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
         prog.getInsideSearch(pathList).start();

        System.out.println(directory.getAbsolutePath());
        //System.out.println(directory.getName());

        if(directory.canRead()) {
            File[] allFilesIn = directory.listFiles();
            for (int i = 0; i < allFilesIn.length; i++) {
                if (allFilesIn[i].isDirectory() &&
                        !(allFilesIn[i].getAbsolutePath().equals("/proc") ||
                                allFilesIn[i].getAbsolutePath().equals("/sys"))
                )
                    searchUtil(allFilesIn[i]);
            }
        }
    }
}
