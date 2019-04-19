package frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;

public interface IMainPageController extends Initializable {

    /**
     * Controls behaviour of the app, when the button "openSettings" is pressed
     */
    @FXML
    void openSettingsPage() throws IOException;

    /**
     * Controls behaviour of the app, when the button "openReset" is pressed
     */
    @FXML
    void openResetPage() throws IOException;

}
