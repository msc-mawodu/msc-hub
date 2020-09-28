package msc.mawodu.hub.stores;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FilenamesStoreMySQL implements FilenamesStore {

    private static final String FILE_TABLE_NAME = "hub.file";

    private JdbcTemplate jdbc;

    public FilenamesStoreMySQL(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<String> fetchAllFilenames(String pipelineId) {
        List<String> fileNamesForPipeline = new ArrayList<>();
        jdbc.query("SELECT * FROM " + FILE_TABLE_NAME + " WHERE pipeline_id = ?", result -> {
            String fileName = result.getString(3);
            fileNamesForPipeline.add(
                    result.getString(3)
            );
        }, pipelineId);

        return fileNamesForPipeline;

    }

    @Override
    public boolean registerNewFile(String pipelineId, String fileName) {
        jdbc.update("insert into " + FILE_TABLE_NAME +
                        " (pipeline_id, file_name)" +
                        " values (?, ?)",
                pipelineId,
                fileName);
        return true;
    }
}
