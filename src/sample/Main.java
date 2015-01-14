/**
 * Created by Bartosz Nowak on 2015-01-09.
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setStage(primaryStage);
        mainController.addBindings();
        primaryStage.setTitle("Notes");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
