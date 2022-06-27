package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import it.city.edu.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyAware extends AbsNameEntity {

    @ManyToOne
    private Aware aware;

    @ManyToOne
    private CompanyInfo companyInfo;
}

