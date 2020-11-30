package org.example.multimodule.services.db.impl;

import lombok.AllArgsConstructor;
import org.example.multimodule.dao.MigrationPassportDao;
import org.example.multimodule.dao.MvsPassportDao;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.example.multimodule.models.SOAPPassport;
import org.example.multimodule.services.db.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class PassportServiceImpl implements PassportService {

    private final MvsPassportDao mvsPassportDao;
    private final MigrationPassportDao migrationPassportDao;

    @Override
    public List<SOAPPassport> find(String serie, String number) {
        return null;
    }
}
