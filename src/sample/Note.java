/**
 * Created by Bartosz Nowak on 2015-01-07.
 */

package sample;

import java.time.LocalDate;

public class Note implements Comparable<Note> {

    private String title;
    private String content;
    private LocalDate completionDate;
    private Boolean highPriority;
    private String noteCategory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Boolean getHighPriority() {
        return highPriority;
    }

    public void setHighPriority(Boolean highPriority) {
        this.highPriority = highPriority;
    }

    public String getNoteCategory() {
        return noteCategory;
    }

    public void setNoteCategory(String noteCategory) {
        this.noteCategory = noteCategory;
    }

    @Override
    public int compareTo(Note otherNote) {
        if (getCompletionDate().isAfter(otherNote.getCompletionDate())) {
            return 1;
        } else if (getCompletionDate().isBefore(otherNote.getCompletionDate())) {
            return -1;
        } else {
            return 0;
        }
    }
}
