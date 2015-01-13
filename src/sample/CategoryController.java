package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * Created by Bartosz Nowak on 2015-01-09.
 */
public class CategoryController {

    @FXML private Button cancelButton;
    @FXML private Button addCategoryButton;
    @FXML private TextField categoryName;

    private NoteController noteController;


    public void addCategory(Event event) {

        final Tooltip tooltip = new Tooltip();
        tooltip.setText("To pole nie może być puste");

        if (categoryName.getText() != null && ! categoryName.getText().trim().isEmpty()) {

            NoteCategories.INSTANCE.addCategory(categoryName.getText());
            noteController.refreshCategoryList();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {
            categoryName.setStyle("-fx-text-box-border: red ;");
            categoryName.setTooltip(tooltip);
        }
    }

    public void addParentControllerBinding(NoteController noteController) {
        this.noteController = noteController;
    }

    public void cancel(Event event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
