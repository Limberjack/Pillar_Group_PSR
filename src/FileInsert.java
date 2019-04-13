import database.DBrequest;
import database.modelsDB.ConfigDB;
import database.modelsDB.ConfigFileDB;
import database.modelsDB.ProgramDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileInsert {
    /**
     *
     * @param program
     */
    public static void copyFile(ProgramDB program) {
        List<ConfigDB> configDBList = DBrequest.query(program);
        List<ConfigFileDB> configFilesDB = configDBList.get(0).getConfigFiles();
        configFilesDB.forEach(configFileDB -> copyFile(configFileDB.getPathBackup(), configFileDB.getPathProgram()));
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
