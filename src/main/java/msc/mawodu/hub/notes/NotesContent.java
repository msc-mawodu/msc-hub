package msc.mawodu.hub.notes;

import javax.validation.constraints.NotBlank;

public class NotesContent {

    @NotBlank(message = "Updated notes cannot be empty!")
    String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
