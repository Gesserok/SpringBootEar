package org.example.multimodule.services;

import org.example.multimodule.models.MVSUkrPassport;

import java.util.List;

public interface MvsPassportService {
    List<MVSUkrPassport> findByNumber(String number);

    List<MVSUkrPassport> findBySeriesAndNumber(String series, String number);

}
