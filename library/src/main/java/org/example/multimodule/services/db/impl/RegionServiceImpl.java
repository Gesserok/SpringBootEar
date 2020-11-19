package org.example.multimodule.services.db.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dao.RegionRepository;
import org.example.multimodule.models.Region;
import org.example.multimodule.services.db.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }
    @Override
    public void deleteAllByResourceId(String resourceId) {
        regionRepository.deleteAllByResourceId(resourceId);
    }
}
