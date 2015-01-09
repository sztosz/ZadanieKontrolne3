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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


/**
 * Created by Bartosz Nowak on 2015-01-08.
 */
public class NoteController {

    @FXML private Slider prioritySlider;
    @FXML private TextField noteTitle;
    @FXML private TextArea noteContent;
    @FXML private DatePicker completionDate;
    @FXML private Label highPriorityLabel;
    @FXML private Label lowPriorityLabel;
    @FXML private ComboBox<String> categoryList;

    List<Note> notes;


    public void addBinding(List<Note> notes) {
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
//                    noteTitle.setText(newValue.toString());
                }
            }
        });
//        noteTitle.textProperty().bind(categoryList.getSelectionModel().selectedItemProperty());
//        noteTitle.setText(categoryList.getSelectionModel().getSelectedItem());
        this.notes = notes;
        refreshCategoryList();

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

//            mainWindowStage.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshCategoryList() {
        categoryList.getItems().clear();
        categoryList.getItems().addAll(NoteCategories.INSTANCE.getCategories());
    }

    public void cancel(Event event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void addNote(Event event) {
        Note note = new Note();
        note.setTitle(noteTitle.getText());
        note.setContent(noteContent.getText());
        note.setCompletionDate(completionDate.getValue());
        Boolean highPriority = (prioritySlider.getValue() != 0);
        note.setHighPriority(highPriority);
        note.setNoteCategory(categoryList.getValue());
//        int i = 1;
        ((Node)event.getSource()).getScene().getWindow().hide();

        notes.add(note);
    }
}
