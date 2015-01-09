package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartosz Nowak on 2015-01-07.
 */


public enum NoteCategories {
    INSTANCE;

    private final List<String> categories = new ArrayList<String>();

    public String[] getCategories() {
        String[] noteCategories = new String[categories.size()];
        noteCategories = categories.toArray(noteCategories);
        return (noteCategories);
    }

    public void addCategory(String category) {
        categories.add(category);
    }
}
