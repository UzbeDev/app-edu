package it.city.edu.entity;

import it.city.edu.entity.template.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class CompanyType extends AbsNameEntity {

}