package lib;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

public class Checker implements IChecker {

    private PropertiesHandler ph;

    @Override
    public boolean checkFirstTime() {
        this.ph = new PropertiesHandler(new File(References.CONFIX_SETTINGS_FILE_PATH));
        String propertyName = "first.time";
        try {
            return ph.get(propertyName).equals("true");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Property " + propertyName + " was not found");
        }
    }

    @Override
    public boolean checkOpenSettings() {
        this.ph = new PropertiesHandler(new File(References.CONFIX_SETTINGS_FILE_PATH));
        String propertyName = "open.settings";
        try {
            return ph.get(propertyName).equals("true");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Property " + propertyName + " was not found");
        }
    }
}
