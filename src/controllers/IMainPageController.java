package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;

public interface IMainPageController extends Initializable {

    @FXML
    void openSettingsPage() throws IOException;

    @FXML
    void openResetPage() throws IOException;

}
