package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MainController {
    @FXML private Button addNoteButton;
    @FXML private ListView<String> notesList;

    public void populateNotesList() {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App");
        notesList.setItems(items);
    }

    public void addNote() {

    }
}
