package org.example.multimodule.dao;

import org.example.multimodule.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    void deleteAllByResourceId(String resourceId);

}
