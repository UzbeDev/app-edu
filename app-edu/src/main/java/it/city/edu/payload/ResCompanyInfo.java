package it.city.edu.payload;

import it.city.edu.entity.Aware;
import it.city.edu.entity.CompanyInfo;
import it.city.edu.entity.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCompanyInfo {
    private UUID id;
    private String brandName;
    private String definition;
}
