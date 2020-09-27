package msc.mawodu.hub.providers;

import msc.mawodu.hub.pipelines.PipelineDetails;
import msc.mawodu.hub.pipelines.PipelineOverview;
import msc.mawodu.hub.status.PipelineMetaData;
import msc.mawodu.hub.stores.FilenamesStore;
import msc.mawodu.hub.stores.NotesStore;
import msc.mawodu.hub.stores.PipelineMetadataStore;

import java.util.List;
import java.util.Optional;

public class BasePipelineDetailsDataProvider implements PipelineDetailsDataProvider {

    private NotesStore notesDatabase;
    private PipelineMetadataStore metadataStore;
    private FilenamesStore filenamesDatabase;

    public BasePipelineDetailsDataProvider(NotesStore notesDatabase, PipelineMetadataStore metadataStore, FilenamesStore filenamesDatabase) {
        this.notesDatabase = notesDatabase;
        this.metadataStore = metadataStore;
        this.filenamesDatabase = filenamesDatabase;
    }

    @Override
    public PipelineDetails fetchById(String uid) {
        Optional<PipelineMetaData> pipelineMetaData = metadataStore.fetch(uid);

        PipelineMetaData metaData;
        if (!pipelineMetaData.isPresent()) {
            return null;
        }
        metaData = pipelineMetaData.get();

        String pipelineNotes = notesDatabase.fetchNotesByPipelineId(uid);

        List<String> pipelineFiles = filenamesDatabase.fetchAllFilenames(uid);

        PipelineOverview overview = new PipelineOverview(metaData.getId(), metaData.getState(), pipelineFiles.size(), metaData.getDescription());

        if (null == overview) {
            return null;
        }

        return new PipelineDetails(overview, pipelineNotes, pipelineFiles);
    }
}
