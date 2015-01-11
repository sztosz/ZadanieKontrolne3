package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    @FXML private Button addNoteButton;
    @FXML private ListView<Note> notesList;

    List<Note> notes = new ArrayList<Note>();
    ObservableList<Note> noteObservableList = FXCollections.observableList(notes);


    public void addBindings() {
//        addNoteButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                addNote(event);
//            }
//        });
//        ObservableList<Note> noteObservableList = FXCollections.observableList(notes);
        notesList.setItems(noteObservableList); // TODO fix it across the board to use proper ObservableList and proper name
        notesList.getItems().sort(Comparator.<Note>naturalOrder());
        notesList.setCellFactory(new Callback<ListView<Note>, ListCell<Note>>() {
            @Override
            public ListCell<Note> call(ListView<Note> param) {

                final ListCell<Note> listCell = new ListCell<Note>(){
                    @Override
                    protected void updateItem(Note note, boolean empty) {
                        super.updateItem(note, empty);
                        if (note != null) {
                            setText(note.getTitle() + ", " + note.getNoteCategory() + ", " + note.getCompletionDate());
                            if (note.getHighPriority()) {
                                setStyle("-fx-text-fill: red;");
                            }
                        } else if (empty) {
                            setText("");
                        }

                    }

                };
                final ContextMenu listCellMenu = new ContextMenu();
                MenuItem removeItem = new MenuItem("Usuń");
                removeItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        notesList.getItems().remove(listCell.getItem());
                    }
                });
                listCellMenu.getItems().add(removeItem);
                listCell.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(listCell.itemProperty()))
                        .then(listCellMenu)
                        .otherwise((ContextMenu)null)
                );
                return listCell;
            }
        });
//        notesList.set;



    }

    public void addNote(Event event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addNoteWindow.fxml"));
            Parent root = fxmlLoader.load();
            NoteController noteController = fxmlLoader.getController();
            noteController.addBinding(noteObservableList);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Dodaj Notatkę");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

//            mainWindowStage.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeNote(ActionEvent event) {
        System.out.println(event.getTarget().toString());
    }
}
