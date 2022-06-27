package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact extends AbsEntity {

    @ManyToOne(optional = false)
    private District district;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fax;

   @ElementCollection
    private Set<String> phoneNumbers;
}
