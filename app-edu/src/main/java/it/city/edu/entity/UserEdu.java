package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEdu extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    @JoinTable(name = "user_edu_legal",
            joinColumns = @JoinColumn(name = "user_edu_id"),
            inverseJoinColumns = @JoinColumn(name = "legal_id") )
    private Set<Legal> legals;


    @ManyToOne(fetch = FetchType.LAZY)
    private EduCenter eduCenter;

}
