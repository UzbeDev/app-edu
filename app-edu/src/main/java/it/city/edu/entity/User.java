package it.city.edu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.city.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String middleName;

    @Column(nullable = false)
    private String passSerial;

    @Column(nullable = false)
    private String passNumber;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Contact contact;

    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private Set<Role> roles;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserEdu> userEdus;

    private boolean enabled;

    private boolean credentialsNonExpired = true;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;


    public User(String firstName, String lastName, String middleName, String passportSerial, String passportNumber, Date birthDate, String phoneNumber, String password, Contact contact, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passSerial = passportSerial;
        this.passNumber = passportNumber;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.contact = contact;
        this.roles = roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return passSerial+passNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
