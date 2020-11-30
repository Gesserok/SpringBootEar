package org.example.multimodule.services;

import org.example.multimodule.models.MigrationServiceUrkPassport;

import java.util.List;

public interface MigrationPassportService {
    List<MigrationServiceUrkPassport> findByNumber(String number);

    List<MigrationServiceUrkPassport> findBySeriesAndNumber(String series, String number);

}
