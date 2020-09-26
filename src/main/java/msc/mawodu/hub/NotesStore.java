package msc.mawodu.hub;

public interface NotesStore {
    String fetchNotesByPipelineId(String pipelineId);
    boolean updatePipelineNotes(String pipelineId, String updatedNotes);
}
