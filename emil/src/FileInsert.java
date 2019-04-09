import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class FileInsert {
    public void copyFile(String originFilePath, String destinationFolderPath)
            throws Exception
    {
        File orig = new File(originFilePath);

        if (!orig.exists() || !orig.isFile()) {
            throw new FileNotFoundException("Origin is invalid");
        }
        destinationFolderPath = destinationFolderPath+"\\"+orig.getName();
        File dest = new File(destinationFolderPath);
        Files.copy(orig.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}
