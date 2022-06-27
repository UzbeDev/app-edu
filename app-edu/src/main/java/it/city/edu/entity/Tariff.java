package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tariff extends AbsEntity {
    
    @Column(nullable = false, unique = true)
    private Integer groupAmount;

    @Column(nullable = false)
    private double paySum;
}
