package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lib.Checker;
import lib.PropertiesHandler;
import lib.PropertiesSetter;
import lib.References;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainPageController implements IMainPageController{

    private Checker checker;
    private PropertiesHandler ph;

    @FXML
    @Override
    public void openSettingsPage() throws IOException {
        if (checker.checkFirstTime()) {
            Runtime.getRuntime().exec(References.CMD_GET_APPS_NAMES_COMMAND);
            ph.save("first-time", "false");
            // TODO
            // Вставить код для FileGetter'a
        }
        ph.save("open-settings", "true");
        Parent root = FXMLLoader.load(getClass().getResource(References.SETTINGS_PAGE_FXML_PATH));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Настройки");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    @Override
    public void openResetPage() throws IOException {
        Parent root;
        if(!checker.checkOpenSettings()) {
            root = FXMLLoader.load(getClass().getResource(References.RESET_FIRSTTIME_PAGE_FXML_PATH));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Ошибка!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            root = FXMLLoader.load(getClass().getResource(References.RESET_PAGE_FXML_PATH));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Восстановить");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PropertiesSetter ps = new PropertiesSetter();
        ph = new PropertiesHandler(new File(References.CONFIX_SETTINGS_FILE_PATH));
        checker = new Checker();
        ps.initialize();
    }
}
