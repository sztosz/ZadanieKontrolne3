/**
 * Created by Bartosz Nowak on 2015-01-08.
 */

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class NoteController {

    @FXML private Button addButton;
    @FXML private Slider prioritySlider;
    @FXML private TextField noteTitle;
    @FXML private TextArea noteContent;
    @FXML private DatePicker completionDate;
    @FXML private Label highPriorityLabel;
    @FXML private Label lowPriorityLabel;
    @FXML private ComboBox<String> categoryList;

    private List<Note> notes;
    private Note note;
    private Boolean IamNotEdited = true;

    public void addBinding(List<Note> notes, Note note) {
        this.notes = notes;

        prioritySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue == null) {
                    highPriorityLabel.setStyle("-fx-text-fill: green;");
                    lowPriorityLabel.setStyle("-fx-text-fill: green;");
                } else {
                    if (newValue.intValue() == 0) {
                        lowPriorityLabel.setStyle("-fx-text-fill: red;");
                        highPriorityLabel.setStyle("-fx-text-fill: green;");
                    } else if (newValue.intValue() == 1) {
                        lowPriorityLabel.setStyle("-fx-text-fill: green;");
                        highPriorityLabel.setStyle("-fx-text-fill: red;");
                    }
                }
            }
        });
        refreshCategoryList();
        this.note = note;
        if (note.getTitle() != null) {
            fillFormFromNote();
            IamNotEdited = false;
            addButton.setText("Edytuj Notatkę");
        }
    }

    private void fillFormFromNote() {
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
        completionDate.setValue(note.getCompletionDate());
        Double priority = note.getHighPriority() ? 1.0 : 0.0;
        prioritySlider.setValue(priority);
        categoryList.getSelectionModel().select(note.getNoteCategory());
    }

    public void addCategory(Event event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addCategoryDialog.fxml"));
            Parent root = fxmlLoader.load();
            CategoryController categoryController = fxmlLoader.getController();
            categoryController.addParentControllerBinding(this);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setTitle("Dodaj Kategorię");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshCategoryList() {
        categoryList.getItems().clear();
        categoryList.getItems().addAll(NoteCategories.INSTANCE.getCategories());
        categoryList.getSelectionModel().select(0);
    }

    public void cancel(Event event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void addNote(Event event) {
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("To pole nie może być puste");

        if (checkNoteTitle(tooltip) &&  // TODO: Java does not check if all expressions it stops at first false. No simple way to go over it.
            checkNoteContent(tooltip) &&
            checkCompletionDate(tooltip)) {
            note.setHighPriority(prioritySlider.getValue() != 0);
            note.setNoteCategory(categoryList.getValue());
            if (IamNotEdited) {
                notes.add(note);
            } else {
                int index = notes.indexOf(note);  // TODO: It's an ugly hack. Fix it when you can find some reliable solution.
                notes.remove(note);
                notes.add(index, note);
            }
            ((Node)event.getSource()).getScene().getWindow().hide();
        }
    }

    private Boolean checkNoteTitle(Tooltip tooltip) {
        if (noteTitle.getText() != null && ! noteTitle.getText().trim().isEmpty()) {
            note.setTitle(noteTitle.getText());
            noteTitle.setStyle("-fx-text-box-border: lightgray");
            return true;
        } else {
            noteTitle.setStyle("-fx-text-box-border: red");
            noteTitle.setTooltip(tooltip);
            return false;
        }
    }

    private Boolean checkNoteContent(Tooltip tooltip) {
        if (noteContent.getText() != null && ! noteContent.getText().trim().isEmpty()) {
            note.setContent(noteContent.getText());
            noteContent.setStyle("-fx-text-box-border: lightgray");
            return true;
        } else {
            noteContent.setStyle("-fx-text-box-border: red");
            noteContent.setTooltip(tooltip);
            return false;
        }
    }

    private Boolean checkCompletionDate(Tooltip tooltip) {
        if (completionDate.getValue() != null) {
            note.setCompletionDate(completionDate.getValue());
            completionDate.setStyle("-fx-background-color: lightgray");
            return true;
        } else {
            completionDate.setStyle("-fx-background-color: red");
            completionDate.setTooltip(tooltip);
            return false;
        }
    }
}
