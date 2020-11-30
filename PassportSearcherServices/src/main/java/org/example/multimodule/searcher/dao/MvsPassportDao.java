package org.example.multimodule.searcher.dao;

import lombok.AllArgsConstructor;
import org.example.multimodule.models.MVSUkrPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MvsPassportDao {

    private static final String SELEC_BY_NUMBER =
            ("SELECT external_id externalId, d_series dSeries, d_number dNumber, d_status, d_type, theft_data, insert_date, ovd " +
                    "FROM mvs_ukr_passports " +
                    " WHERE " +
                    "d_number = ?");

    private static final String SELEC_BY_SERIES_NUMBER =
            ("SELECT external_id externalId, d_series dSeries, d_number dNumber, d_status, d_type, theft_data, insert_date, ovd " +
                    "FROM mvs_ukr_passports " +
                    " WHERE " +
                    "d_series = ? " +
                    "AND d_number = ?");

    private final JdbcTemplate jdbcTemplate;

    public List<MVSUkrPassport> findByDSeriesAndDNumber(String series, String number) {
        return jdbcTemplate.query(
                SELEC_BY_SERIES_NUMBER
                , new BeanPropertyRowMapper<>(MVSUkrPassport.class)
                , series
                , number);
    }

    public List<MVSUkrPassport> findByDNumber(String number) {
        return jdbcTemplate.query(
                SELEC_BY_NUMBER
                , new BeanPropertyRowMapper<>(MVSUkrPassport.class)
                , number);

    }
}
