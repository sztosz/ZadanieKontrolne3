package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = fxmlLoader.load();
//        primaryStage.initModality(Modality.WINDOW_MODAL);
        MainController mainController = fxmlLoader.getController();
//        mainController.setStage(primaryStage);
        mainController.addBindings();
        primaryStage.setTitle("Notes");
        primaryStage.setScene(new Scene(root));


        mainController.populateNotesList();  // TODO: Remove unnecessary code

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
