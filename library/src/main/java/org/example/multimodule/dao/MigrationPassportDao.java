package org.example.multimodule.dao;

import lombok.AllArgsConstructor;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MigrationPassportDao {

    private static final String SELECT_BY_NUMBER =
            ("SELECT OVD_NUMBER nn, D_SERIES_TRANSFORMED dSeries, D_DOC_NUM_TRANSFORMED dNumber, STATUS, DATE_EDIT " +
                    "FROM " +
                    "migration_passports " +
                    "WHERE D_DOC_NUM_TRANSFORMED = ?").toLowerCase();

    private static final String SELECT_BY_SERIES_NUMBER =
            ("SELECT OVD_NUMBER nn, D_SERIES_TRANSFORMED dSeries, D_DOC_NUM_TRANSFORMED dNumber, STATUS, DATE_EDIT " +
                    "FROM " +
                    "migration_passports " +
                    "WHERE D_DOC_SERIES_TRANSFORMED = ? " +
                    "AND D_DOC_NUM_TRANSFORMED = ?").toLowerCase();

    private final JdbcTemplate jdbcTemplate;

    public List<MigrationServiceUrkPassport> findByDSeriesAndDNumber(String series, String number) {
        return jdbcTemplate.query(
                SELECT_BY_SERIES_NUMBER
                , new BeanPropertyRowMapper<>(MigrationServiceUrkPassport.class)
                , series
                , number
        );
    }

    public List<MigrationServiceUrkPassport> findByDNumber(String number) {
        return jdbcTemplate.query(
                SELECT_BY_NUMBER
                , new BeanPropertyRowMapper<>(MigrationServiceUrkPassport.class)
                , number
        );
    }
}
