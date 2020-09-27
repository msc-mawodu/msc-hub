package msc.mawodu.hub;

public class Routes {

    public static final String HOME = "/";
    public static final String PIPELINE_DETAILS = "/pipeline/{uid}";
    public static final String PIPELINE_NOTES_UPDATE = "/note/{pipelineId}";
    public static final String PIPELINE_FILE_UPLOAD = "/upload/{pipelineId}";
    public static final String PIPELINE_MANUAL_FILE_UPLOAD = "/file/manual/{pipelineId}";
    public static final String PIPELINE_FILE_DOWNLOAD = "/file/{pipelineId}/{fileName}";
    public static final String PIPELINE_STATUS = "/status";
    public static final String PIPELINE_PROFILING_DATA = "/ps/{pipelineId}";

    public static final String PIPELINE_MANUAL_FILE_UPLOADED = "uploaded-file";

}
