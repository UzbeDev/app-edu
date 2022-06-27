package it.city.edu.projection;

import it.city.edu.entity.District;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = District.class)
public interface CustomDistrict {
    Integer getId();

    String getNameUz();

    String getNameRu();

    String getNameEn();

    @Value("#{target.region.id}")
    Integer getRegionId();

}
