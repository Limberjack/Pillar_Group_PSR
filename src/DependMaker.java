import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class DependMaker {

    /**
     * @param supProgs_txt текстовый файл, содержащий наименования поддерживаемых программ
     * @return список поддерживаемых программ
     * @throws FileNotFoundException
     */
    public static LinkedList<Prog> buildDependence(File supProgs_txt) throws FileNotFoundException {
        LinkedList<Prog> supportedProgList1 = new LinkedList<>();
        Scanner read = new Scanner(supProgs_txt);
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
        new File("./progDescription").mkdir();
        File fileToReturn  = new File("./progDescription/Progs.txt");
        fileToReturn.createNewFile();
        return  fileToReturn;
    }

    public static void bringFiles(File chosenProgs) throws FileNotFoundException {
        File configurationFolder = new File("./all_configs");
        configurationFolder.mkdir();

        Scanner read = new Scanner(chosenProgs);
        //TODO добавь метод с нахождением имени программы и ее версии
        //TODO на основе этого сделай отбор конфигов и их клонирование в созданную папку
        File configFile = new File(configurationFolder.getAbsolutePath() + getProgName(read));

    }

    private static String getProgName(Scanner read){
        return read.nextLine().substring(2);
    }
}
