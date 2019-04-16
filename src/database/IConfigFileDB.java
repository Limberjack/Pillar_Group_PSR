package database;

/**
 * Use this interface for the file which contains to the general config
 */
public interface IConfigFileDB {
    String getFileName();
    String getFilePathProgram();
    String getFilePathBackup();
}