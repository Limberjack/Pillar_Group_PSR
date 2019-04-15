import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.References;

public class MainPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/pages/main_page.fxml"));
        primaryStage.setTitle("ConFix");
        primaryStage.setResizable(false);
        primaryStage.setIconified(true);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
