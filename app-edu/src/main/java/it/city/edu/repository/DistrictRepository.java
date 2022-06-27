package it.city.edu.repository;

import it.city.edu.entity.District;
import it.city.edu.projection.CustomDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "district", excerptProjection = CustomDistrict.class)
public interface DistrictRepository extends JpaRepository<District, Integer> {
}
