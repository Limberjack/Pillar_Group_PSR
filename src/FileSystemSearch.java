import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class FileSystemSearch {
    public static void initiateSearch(SupportedProgList supportedProgList){
        initializeSearch(supportedProgList);
    }

    private static void initializeSearch(SupportedProgList supportedProgList){
        Scanner fileRead = new Scanner("SupportedPrograms");
        while(fileRead.hasNext())
            supportedProgList.add(new Prog(fileRead.nextLine()));
        searchUtil(new File("/"));
    }

    private static void searchUtil(File directory ){
        /**  Prog prog = supportedProgList.getProg(directory.getName());
         if(prog != null)
         prog.getInsideSearch().start();*/

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
