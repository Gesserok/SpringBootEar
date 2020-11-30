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
            ("SELECT EXTERNAL_ID extId, D_SERIES dSeries, D_NUMBER dNumber, D_STATUS, D_TYPE, THEFT_DATA, INSERT_DATE, OVD " +
                    "FROM mvs_ukr_passports " +
                    " WHERE " +
                    "D_NUMBER = ?").toLowerCase();

    private static final String SELEC_BY_SERIES_NUMBER =
            ("SELECT EXTERNAL_ID extId, D_SERIES dSeries, D_NUMBER dNumber, D_STATUS, D_TYPE, THEFT_DATA, INSERT_DATE, OVD " +
                    "FROM mvs_ukr_passports " +
                    " WHERE " +
                    "D_SERIES = ? " +
                    "AND D_NUMBER = ?").toLowerCase();

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
