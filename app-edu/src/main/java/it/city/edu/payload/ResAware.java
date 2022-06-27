package it.city.edu.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResAware {

    private Integer id;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String color;
}
