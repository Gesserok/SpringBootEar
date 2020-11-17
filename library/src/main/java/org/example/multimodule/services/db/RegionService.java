package org.example.multimodule.services.db;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dao.RegionRepository;
import org.example.multimodule.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class RegionService {

    private final RegionRepository regionRepository;

    public void save(Region region) {
        regionRepository.save(region);
    }

    public void deleteAllByResourceId(String resourceId) {
        regionRepository.deleteAllByResourceId(resourceId);
    }
}
