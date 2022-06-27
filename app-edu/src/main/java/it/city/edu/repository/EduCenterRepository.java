package it.city.edu.repository;

import it.city.edu.entity.EduCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface EduCenterRepository extends JpaRepository<EduCenter, UUID> {

    boolean existsByBrandEqualsIgnoreCase(String brand);
    boolean existsByBrandEqualsIgnoreCaseAndIdNot(String brand, UUID id);

    boolean existsByTinEqualsIgnoreCase(String tin);
    boolean existsByTinEqualsIgnoreCaseAndIdNot(String tin, UUID id);

    boolean existsByNameEqualsIgnoreCase(String name);
    boolean existsByNameEqualsIgnoreCaseAndIdNot(String name, UUID id);


}
