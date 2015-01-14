/**
 * Created by Bartosz Nowak on 2015-01-07.
 */

package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NoteCategories {
    INSTANCE;

    private final List<String> categories = new ArrayList<>(Arrays.asList("Prywatne"));

    public String[] getCategories() {
        String[] noteCategories = new String[categories.size()];
        noteCategories = categories.toArray(noteCategories);
        return (noteCategories);
    }

    public void addCategory(String category) {
        categories.add(category);
    }
}
