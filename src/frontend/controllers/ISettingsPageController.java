package frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public interface ISettingsPageController extends Initializable {

    /**
     * Selects all items in the list
     */
    @FXML
    void selectAll();

    /**
     * Saves the selected programs to database
     * as "needed-to-control-configs-of"
     */
    @FXML
    void saveSettings();

}
