package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResetPageFTController implements IResetPageFTController {

    @FXML
    @Override
    public void closeWindow(Event e) {
        Node source = (Node)e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
