package it.city.edu.projection;

import it.city.edu.entity.Region;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Region.class)
public interface CustomRegion {
    Integer getId();
    String getNameUz();
    String getNameRu();
    String getNameEn();

}
