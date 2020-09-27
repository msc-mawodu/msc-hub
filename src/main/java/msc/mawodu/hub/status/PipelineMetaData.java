package msc.mawodu.hub.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PipelineMetaData {

    @JsonProperty("id") private final String id;
    @JsonProperty("state") private final String state;
    @JsonProperty("ip") private final String ip;
    @JsonProperty("description") private final String description;

    public PipelineMetaData(String id, String state, String ip, String description) {
        this.id = id;
        this.state = state;
        this.ip = ip;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getIp() {
        return ip;
    }

    public String getDescription() {
        return description;
    }
}
