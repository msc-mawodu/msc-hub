package msc.mawodu.hub.stores;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class NotesStoreMySQL implements NotesStore {

    private static final String NOTES_TABLE_NAME = "hub.note";

    private JdbcTemplate jdbc;

    public NotesStoreMySQL(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public String fetchNotesByPipelineId(String pipelineId) {
        List<String> notes = new ArrayList<>();
        jdbc.query("SELECT * FROM " + NOTES_TABLE_NAME + " WHERE pipeline_id = ? ORDER BY id DESC", result -> {
            while (result.next()) {
                notes.add(result.getString(3));
            }
        }, pipelineId);

        return notes.get(0);
    }

    @Override
    public boolean updatePipelineNotes(String pipelineId, String updatedNotes) {
        jdbc.update("insert into " + NOTES_TABLE_NAME +
                        " (pipeline_id, note_content)" +
                        " values (?, ?)",
                pipelineId,
                updatedNotes);
        return true;
    }
}
