package it.city.edu.repository;

import it.city.edu.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, UUID> {
    boolean existsByNameEqualsIgnoreCaseOrBrandName(String name, String brandName);

    boolean existsByNameEqualsIgnoreCaseOrBrandNameEqualsIgnoreCaseAndIdNot(String name, String brandName, UUID id);
}
