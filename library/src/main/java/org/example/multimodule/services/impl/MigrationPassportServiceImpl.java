package org.example.multimodule.services.impl;

import lombok.AllArgsConstructor;
import org.example.multimodule.dao.MigrationPassportDao;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.example.multimodule.services.MigrationPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MigrationPassportServiceImpl implements MigrationPassportService {

    private final MigrationPassportDao migrationPassportDao;

    @Override
    public List<MigrationServiceUrkPassport> findByNumber(String number) {
        return migrationPassportDao.findByDNumber(number);
    }

    @Override
    public List<MigrationServiceUrkPassport> findBySeriesAndNumber(String series, String number) {
        return migrationPassportDao.findByDSeriesAndDNumber(series, number);
    }
}
