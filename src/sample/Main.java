package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainNoteWindow.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        primaryStage.setTitle("Notes");
        primaryStage.setScene(new Scene(root, 800, 1275));

        mainController.populateNotesList();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
