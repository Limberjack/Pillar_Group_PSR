package frontend.lib;

import java.io.*;
import java.util.Properties;

public class PropertiesHandler implements IPropertiesHandler{

    private Properties properties;
    private FileInputStream fis;

    public PropertiesHandler(File file) {
        try {
            this.fis = new FileInputStream(file);
            this.properties = new Properties();
            this.properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + file + " was not found");
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load FileInputStream");
        }
    }

    @Override
    public void save(String key, String value) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(References.CONFIX_SETTINGS_FILE_PATH));
            properties.setProperty(key, value);
            properties.store(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File with name " + References.CONFIX_SETTINGS_FILE_PATH + "was not found");
        } catch (IOException e) {
            throw new IllegalStateException("Can't store property with " + fos.toString());
        }
    }

    @Override
    public String get(String key) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            throw new IllegalArgumentException("Property " + key +" was not found");
        }
    }
}
