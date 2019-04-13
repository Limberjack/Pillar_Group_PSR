import java.io.File;
import java.io.FileNotFoundException;

public class FileGetter {
    /**
     * 1)создаем список поддерживаемых программ
     * 2)запускаем полный обход по файловой системе в поисках этих программ
     * 3)спрашиваем пользователя, какие из найденых программ он хочет сохранить
     * 4)идем к программам, указанным юзверем
     * 5)копируем их файлы в свою папку
     * 6)завершаемся, подчищая за собой
     *
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        SupportedProgList supportedProgList = DependMaker.buildDependence(new File("SupportedPrograms.txt"));

        FileSystemSearch.initiateSearch(supportedProgList);


    }



}

