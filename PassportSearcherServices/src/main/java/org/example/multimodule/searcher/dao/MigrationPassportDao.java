package org.example.multimodule.searcher.dao;

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
            ("SELECT ovd_number nn, series dSeries, doc_num dNumber, status, date_edit " +
                    "FROM " +
                    "migration_passports " +
                    "WHERE d_number = ?");

    private static final String SELECT_BY_SERIES_NUMBER =
            ("SELECT ovd_number nn, series dSeries, doc_num dNumber, status, date_edit " +
                    "FROM " +
                    "migration_passports " +
                    "WHERE series = ? " +
                    "AND doc_num = ?");

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
