package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import it.city.edu.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EduCenter extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String brand;

    private String  tin;

    private Date foundedDate;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @OneToOne(optional = false)
    private Contact contact;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private Date licenseExpire;

    @ManyToMany
    @JoinTable(name = "edu_center_edu_type",
            joinColumns = @JoinColumn(name = "edu_center_id"),
            inverseJoinColumns = @JoinColumn(name = "edu_type_id")
    )
    private List<EdoType> edoTypes;

    @OneToOne
    private Attachment logo;

    @OneToOne
    private Attachment license;

    @OneToMany
    private List<Attachment> photos;

    private boolean enabled=true;











}
