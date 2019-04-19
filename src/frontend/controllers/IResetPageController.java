package frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public interface IResetPageController extends Initializable {

    /**
     * Selects all items in the list
     */
    @FXML
    void selectAll();

    /**
     * Resets configs of the selected programs
     */
    @FXML
    void reset();


}
