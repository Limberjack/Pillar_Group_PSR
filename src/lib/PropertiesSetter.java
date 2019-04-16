package lib;


import java.io.File;
import java.io.IOException;

public class PropertiesSetter implements IPropertiesSetter{

    private PropertiesHandler handler;
    private File propertiesFile;

    public PropertiesSetter() {
        this.propertiesFile = new File(References.CONFIX_SETTINGS_FILE_PATH);
        if (!propertiesFile.exists()) {
            try {
                propertiesFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException("Unable to create properties file");
            }
        }
        this.handler = new PropertiesHandler(propertiesFile);
    }


    /**
     * Used to initialize properties values on startup of program by
     * using handler.save(String key, String value).
     */
    public void initialize() {
        handler.save("first.time", "true");
        handler.save("open.settings", "false");
        handler.save("db.host", "///");
        handler.save("db.login", "postgres");
        handler.save("db.password", "postgres");
    }
}
