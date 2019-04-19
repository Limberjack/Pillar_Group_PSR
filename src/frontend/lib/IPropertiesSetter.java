package lib;

import java.io.File;

public interface IPropertiesSetter {

    /**
     * Used to initialize properties values on startup of program by
     * using PropertiesHandler.save().
     * @see PropertiesHandler
     */
    void initialize();
}
