package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;



@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyInfo extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "companyInfo")
    private List<CompanyAware> companyAwares;

    @OneToOne
    private Contact contact;

    @Column(columnDefinition = "text")
    private String definition;
}
