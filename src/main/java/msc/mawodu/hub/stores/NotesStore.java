package msc.mawodu.hub.stores;

public interface NotesStore {
    String fetchNotesByPipelineId(String pipelineId);
    boolean updatePipelineNotes(String pipelineId, String updatedNotes);
}
