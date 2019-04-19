package frontend.lib;

import javafx.fxml.FXML;

public interface IChecker{

    /**
     * Checks if it is the first launch of the app
     * @return true, if the app runs first time. Otherwise returns false
     */
    @FXML
    boolean checkFirstTime();

    /**
     * Checks if the page "settings" was opened at least once.
     * @return true, if the "settings" page was opened at least once. Otherwise returns false
     */
    @FXML
    boolean checkOpenSettings();
}
