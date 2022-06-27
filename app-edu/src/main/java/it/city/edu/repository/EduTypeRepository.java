package it.city.edu.repository;

import it.city.edu.entity.Aware;
import it.city.edu.entity.EdoType;
import it.city.edu.projection.CustomAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface EduTypeRepository extends JpaRepository<EdoType, Integer> {


}
