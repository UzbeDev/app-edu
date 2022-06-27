package it.city.edu.projection;

import it.city.edu.entity.Aware;
import it.city.edu.entity.District;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Aware.class, name = "customAware")
public interface CustomAware {
    Integer getId();

    String getNameUz();

    String getNameRu();

    String getNameEn();

    String getColor();

}
