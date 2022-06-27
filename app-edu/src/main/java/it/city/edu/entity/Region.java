package it.city.edu.entity;

import it.city.edu.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Region extends AbsNameEntity {





}
