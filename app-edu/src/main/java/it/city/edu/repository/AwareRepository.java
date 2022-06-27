package it.city.edu.repository;

import it.city.edu.entity.Aware;
import it.city.edu.projection.CustomAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "aware", collectionResourceRel ="list", excerptProjection = CustomAware.class)
public interface AwareRepository  extends JpaRepository<Aware, Integer> {

    @RestResource(path = "/byName")
    List<Aware> findByNameEnContainsIgnoreCaseOrNameUzContainsIgnoreCaseOrNameRuContainsIgnoreCase(String nameEn, String nameUz, String nameRu);
}
