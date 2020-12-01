package org.example.multimodule.searcher.services.db.impl;

import lombok.AllArgsConstructor;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.searcher.dao.MvsPassportDao;
import org.example.multimodule.searcher.services.db.MvsPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MvsPassportServiceImpl implements MvsPassportService {

    private final MvsPassportDao passportDao;

    @Override
    public List<MVSUkrPassport> findByNumber(String number) {
        return passportDao.findByDNumber(number);
    }

    @Override
    public List<MVSUkrPassport> findBySeriesAndNumber(String series, String number) {
        return passportDao.findByDSeriesAndDNumber(series, number);
    }
}
