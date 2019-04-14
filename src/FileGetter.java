import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        SupportedProgList supportedProgList = null;
        try {
            supportedProgList = DependMaker.buildDependence(new File("./SupportedPrograms.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ебаный рот этого казино");
        }

        try {
            FileSystemSearch.initiateSearch(supportedProgList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}

