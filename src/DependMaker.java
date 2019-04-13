import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DependMaker {
    /**
     * @param supProgsTxt текстовый файл, содержащий наименования поддерживаемых программ
     * @return список поддерживаемых программ
     * @throws FileNotFoundException
     */
    public static SupportedProgList buildDependence(File supProgsTxt) throws FileNotFoundException {
        SupportedProgList supportedProgList1 = new SupportedProgList();
        Scanner read = new Scanner(supProgsTxt);
        while(read.hasNext())
            supportedProgList1.add(new Prog(read.nextLine()));
        return supportedProgList1;
    }


    /**
     * делает файл для записи путей найденых программ и возвращает его
     * @return текстовый файл содержащий пути к программам
     * @throws IOException
     */
    public static File pathListGen() throws IOException {
        new File("./progPaths").mkdir();
        File fileToReturn  = new File("./progPaths/ProgPaths.txt");
        fileToReturn.createNewFile();
        return  fileToReturn;
    }
}
