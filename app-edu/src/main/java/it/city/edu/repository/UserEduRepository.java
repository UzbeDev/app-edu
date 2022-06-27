package it.city.edu.repository;

import it.city.edu.entity.EdoType;
import it.city.edu.entity.UserEdu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserEduRepository extends JpaRepository<UserEdu, UUID> {



}
