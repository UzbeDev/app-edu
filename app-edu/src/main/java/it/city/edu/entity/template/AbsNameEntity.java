package it.city.edu.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nameUz;

    @Column(nullable = false, unique = true)
    private String nameRu;

    @Column(nullable = false, unique = true)
    private String nameEn;
}
