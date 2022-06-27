package it.city.edu.entity;

import it.city.edu.entity.enums.LegalName;
import it.city.edu.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Legal implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private LegalName legalName;


    @Override
    public String getAuthority() {
        return legalName.name() ;
    }
}
