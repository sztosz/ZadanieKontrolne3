/**
 * Created by Bartosz Nowak on 2015-01-09.
 */

package sample;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainController {

    Stage mainWindowStage;

    @FXML private ListView<Note> notesListView;

    List<Note> notes = new ArrayList<>();
    ObservableList<Note> notesObservableList = FXCollections.observableList(notes);

    public void addBindings() {
        notesListView.setItems(notesObservableList);
        notesListView.getItems().sort(Comparator.<Note>naturalOrder());
        notesListView.setCellFactory(new Callback<ListView<Note>, ListCell<Note>>() {
            @Override
            public ListCell<Note> call(ListView<Note> param) {

                final ListCell<Note> listCell = new ListCell<Note>() {
                    @Override
                    protected void updateItem(Note note, boolean empty) {
                        super.updateItem(note, empty);
                        if (note != null) {
                            setText(note.getTitle() + ", " + note.getNoteCategory() + ", " + note.getCompletionDate());
                            if (note.getHighPriority()) {
                                setStyle("-fx-text-fill: red;");
                            } else {
                                setStyle("-fx-text-fill: black;");
                            }
                        } else if (empty) {
                            setText("");
                        }
                    }
                };
                final ContextMenu listCellMenu = new ContextMenu();
                MenuItem removeItem = new MenuItem("Usuń");
                removeItem.setOnAction(event -> notesListView.getItems().remove(listCell.getItem()));
                listCellMenu.getItems().add(removeItem);
                MenuItem editItem = new MenuItem("Edytuj");
                editItem.setOnAction(event -> editNote(notesListView.getSelectionModel().getSelectedItem()));
                listCellMenu.getItems().add(editItem);
                listCell.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(listCell.itemProperty()))
                                .then(listCellMenu)
                                .otherwise((ContextMenu) null)
                );
                return listCell;
            }
        });
    }

    public void editNote(Note note){
        showAddNoteWindow(note);
    }

    public void addNote() {
        showAddNoteWindow(new Note());
    }

    private void showAddNoteWindow(Note note){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addNoteWindow.fxml"));
            Parent root = fxmlLoader.load();
            NoteController noteController = fxmlLoader.getController();
            noteController.addBinding(notesObservableList, note);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainWindowStage);
            stage.setTitle("Dodaj Notatkę");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.mainWindowStage = stage;
    }
}
