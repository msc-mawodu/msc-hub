package msc.mawodu.hub.stores;

import msc.mawodu.hub.status.PipelineMetaData;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PipelineMetadataStoreMySQL implements PipelineMetadataStore {

    private JdbcTemplate jdbc;
    private static final String MEAT_TABLE_NAME = "hub.meta";

    public PipelineMetadataStoreMySQL(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<PipelineMetaData> fetch(String pipelineId) {
        return Optional.empty();
    }

    @Override
    public List<PipelineMetaData> fetchAll() {
        List<PipelineMetaData> metaDataList = new ArrayList<>();
        jdbc.query("SELECT * FROM " + MEAT_TABLE_NAME, result -> {
            while (result.next()) {
                metaDataList.add(new PipelineMetaData(
                   result.getString(2),
                   result.getString(4),
                   result.getString(5),
                   result.getString(3)
                ));

            }
        });
        return metaDataList;
    }

    @Override
    public boolean update(PipelineMetaData pipelineMetaData) {
        jdbc.update("insert into " + MEAT_TABLE_NAME +
                        " (pipeline_id, description, status, ip)" +
                        " values (?, ?, ?, ?)",
                pipelineMetaData.getId(),
                pipelineMetaData.getDescription(),
                pipelineMetaData.getState(),
                pipelineMetaData.getIp());
        return true;
    }

    @Override
    public boolean registerNewPipeline(PipelineMetaData pipelineMetaData) {
        jdbc.update("insert into " + MEAT_TABLE_NAME +
                        " (pipeline_id, description, status, ip)" +
                        " values (?, ?, ?, ?)",
                pipelineMetaData.getId(),
                pipelineMetaData.getDescription(),
                pipelineMetaData.getState(),
                pipelineMetaData.getIp());
        return true;
    }
}
