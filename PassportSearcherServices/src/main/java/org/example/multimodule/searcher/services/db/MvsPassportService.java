package org.example.multimodule.searcher.services.db;

import org.example.multimodule.models.MVSUkrPassport;

import java.util.List;

public interface MvsPassportService {
    List<MVSUkrPassport> findByNumber(String number);

    List<MVSUkrPassport> findBySeriesAndNumber(String series, String number);

}
