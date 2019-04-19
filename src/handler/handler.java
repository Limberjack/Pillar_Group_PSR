import database.modelsDB.ConfigDB;
import database.modelsDB.ConfigFileDB;
import database.modelsDB.ProgramDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Handler {

    public void Reader() throws FileNotFoundException {
        Scanner in = new Scanner(new File("Path_list.txt"));
        while (in.hasNext()) {
            if (in.hasNext("@")) {
                List<IConfigFileDB> ConfigFiles = new ArrayList<>();
                String programName = in.nextLine().substring(2);
                String programVersion = in.nextLine().substring(2);
                ProgramDB programDB = new ProgramDB(programName, programVersion);
                String configName = in.nextLine().substring(2);
                ConfigDB configDB = new ConfigDB(configName , ConfigFiles);
                while (in.hasNext("#")) {
                    String pathProgram = "./config/" + programName + "/" + programVersion + "/" + configName;
                    String pathBackup = in.nextLine().substring(2);
                    ConfigFileDB configFileDB = new ConfigFileDB(configName, pathProgram, pathBackup);
                    ConfigFiles.add(configFileDB);
                }
            }
            DataBase.getInstance().save(programDB, configDB);
        }
        in.close();
    }
}