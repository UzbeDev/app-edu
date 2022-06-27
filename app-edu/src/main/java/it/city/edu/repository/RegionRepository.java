package it.city.edu.repository;

import it.city.edu.entity.Region;
import it.city.edu.projection.CustomRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "region",  excerptProjection = CustomRegion.class)
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
