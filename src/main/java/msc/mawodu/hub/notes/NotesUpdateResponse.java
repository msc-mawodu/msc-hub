package msc.mawodu.hub.notes;

public class NotesUpdateResponse {

    private String updatedNotesContent;
    private String errors;

    private NotesUpdateResponse(String updatedNotesContent, String errors) {
        this.updatedNotesContent = updatedNotesContent;
        this.errors = errors;
    }

    public static NotesUpdateResponse correct(String updatedNotes) {
        return new NotesUpdateResponse(updatedNotes, "");
    }

    public static NotesUpdateResponse withError(String errors) {
        return new NotesUpdateResponse("", errors);
    }

    public String getUpdatedNotesContent() {
        return updatedNotesContent;
    }

    public String getErrors() {
        return errors;
    }
}
