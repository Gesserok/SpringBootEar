package org.example.multimodule.service;

import org.example.multimodule.dao.RegionRepository;
import org.example.multimodule.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public void save(Region region) {
        regionRepository.save(region);
    }
}
