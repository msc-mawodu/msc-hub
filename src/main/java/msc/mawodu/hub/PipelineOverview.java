package msc.mawodu.hub;

import msc.mawodu.hub.mocks.Routes;

public class PipelineOverview {
    private String uid;
    private String status;
    private int assiociatedFilesCount;
    private String shortDescription;
    private String url;

    public PipelineOverview(String uid, String status, int assiociatedFilesCount, String shortDescription, String url) {
        this.uid = uid;
        this.status = status;
        this.assiociatedFilesCount = assiociatedFilesCount;
        this.shortDescription = shortDescription;
        this.url = Routes.PIPELINE_DETAILS.replace("{uid}", uid);
    }

    public String getUid() {
        return uid;
    }

    public String getStatus() {
        return status;
    }

    public int getAssiociatedFilesCount() {
        return assiociatedFilesCount;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getUrl() {
        return url;
    }
}
