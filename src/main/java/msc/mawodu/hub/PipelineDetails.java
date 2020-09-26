package msc.mawodu.hub;

import msc.mawodu.hub.PipelineOverview;

import java.util.List;

public class PipelineDetails {
    private PipelineOverview details;
    private String notes;
    private List<String> filePaths;

    public PipelineDetails(PipelineOverview details, String notes, List<String> filePaths) {
        this.details = details;
        this.notes = notes;
        this.filePaths = filePaths;
    }

    public PipelineOverview getDetails() {
        return details;
    }

    public String getNotes() {
        return notes;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }
}
