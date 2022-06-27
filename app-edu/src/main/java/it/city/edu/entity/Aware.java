package it.city.edu.entity;

import it.city.edu.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aware extends AbsNameEntity {
    @Column(nullable = false, unique = true)
    private String color;


}
