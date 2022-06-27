package it.city.edu.repository;

import it.city.edu.entity.Role;
import it.city.edu.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer > {
    Role findByRoleName(RoleName roleName);
}
