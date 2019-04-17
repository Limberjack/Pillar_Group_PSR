import database.IConfigDB;
import database.IConfigFileDB;
import database.IProgramDB;
import database.repositories.ConfigRepositoryJdbcImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

public class FileInsert {
    /**
     * Переносит файлы конфигов определенной программы из места, где они хранятся, в место,
     * где они должны находится.
     * @param program
     */
    public static void copyFile(IProgramDB program) throws SQLException {
        List<IConfigDB> configDBList = ConfigRepositoryJdbcImpl.getInstance().find(program);
        List<IConfigFileDB> configFilesDB = configDBList.get(0).getConfigFiles();
        configFilesDB.forEach(configFileDB -> copyFile(configFileDB.getFilePathBackup(),
                                                            configFileDB.getFilePathProgram()));
    }


    /**
     * Копирует файл originFilePath в destinationFolderPath.
     *
     * @param originFilePath        Путь до файла, который нужно скопировать.
     * @param destinationFolderPath Путь до места, куда нужно копировать файл.
     * @throws FileNotFoundException Если файл
     */
    private static void copyFile(String originFilePath, String destinationFolderPath) {
        try {
            File orig = new File(originFilePath);
            if (!orig.exists() || !orig.isFile()) {
                throw new FileNotFoundException("Origin is invalid");
            }
            destinationFolderPath = destinationFolderPath + "\\" + orig.getName();
            File dest = new File(destinationFolderPath);
            Files.copy(orig.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Irregular file path.");
        }
    }

}
