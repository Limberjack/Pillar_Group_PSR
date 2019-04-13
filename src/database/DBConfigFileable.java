package database;

/**
 * Use this interface for the file which contains to the general config
 */
public interface DBConfigFileable {
    String getFileName();
    String getFilePathProgram();
    String getFilePathBackup();
}