package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public interface ISettingsPageController extends Initializable {

    @FXML
    void selectAll();

    @FXML
    void save();

}
