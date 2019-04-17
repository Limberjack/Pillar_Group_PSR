import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileGetter extends  Thread{
    /**
     * 1)создаем список поддерживаемых программ
     * 2)запускаем полный обход по файловой системе в поисках этих программ
     * 3)спрашиваем пользователя, какие из найденых программ он хочет сохранить
     * 4)идем к программам, указанным юзверем
     * 5)копируем их файлы в свою папку
     * 6)завершаемся, подчищая за собой
     *
     *
     * @param
     * @throws FileNotFoundException
     */
    public static void main(String []arg) {
        LinkedList<Prog> supportedProgList = new LinkedList<>();
        FileWriter writeImportantPaths = null;



        try {                                                                                        // создаем связный список имен поддерживаемых программ
            supportedProgList = DependMaker.buildDependence(new File("./SupportedPrograms.txt"));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("нет файла, содержащего список поддерживаемых программ");
        }
        File pathsToGet = null;


        for (int i = 0; i < supportedProgList.size(); i++) {
            System.out.println(supportedProgList.get(i).getName());
        }
        try {
            pathsToGet = DependMaker.pathListGen();                                                  // создаем файл для записи найденых и подтвержденных путей
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("не удалось сгенерировать файл для записи нужных путей");
        }
        try {
            writeImportantPaths = new FileWriter(pathsToGet);                                        //создаем записыватель в файл с конечными путями
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("нет файла для записи");
        }

        try {
            FileSystemSearch.initiateSearch(supportedProgList, writeImportantPaths);                 //начинаем поиск
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}

