package org.example.multimodule.dao;

import lombok.AllArgsConstructor;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.SOAPPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MvsPassportDao {

    private static final String SELEC_BY_NUMBER =
            ("SELECT EXTERNAL_ID extId, D_SERIES_TRANSFORMED dSeries, D_NUMBER_TRANSFORMED dNumber, D_STATUS, D_TYPE, THEFT_DATA, INSERT_DATE, OVD " +
                    "FROM mvs_ukr_passports " +
                    " WHERE " +
                    "D_NUMBER_TRANSFORMED = ?").toLowerCase();

    private static final String SELEC_BY_SERIES_NUMBER =
            ("SELECT EXTERNAL_ID extId, D_SERIES_TRANSFORMED dSeries, D_NUMBER_TRANSFORMED dNumber, D_STATUS, D_TYPE, THEFT_DATA, INSERT_DATE, OVD " +
                    "FROM mvs_ukr_passports " +
                    " WHERE " +
                    "D_SERIES_TRANSFORMED = ? " +
                    "AND D_NUMBER_TRANSFORMED = ?").toLowerCase();

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
