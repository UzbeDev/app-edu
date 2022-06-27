package it.city.edu.entity;

import it.city.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EduPayment extends AbsEntity {
    @Column(nullable = false)
    private double paySum;

    @Column(nullable = false)
    private Date payDate;

    @ManyToOne(optional = false)
    private PayType payType;
}
