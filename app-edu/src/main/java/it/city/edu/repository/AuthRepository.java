package it.city.edu.repository;

import it.city.edu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    boolean existsByPassSerialEqualsIgnoreCaseAndPassNumber(String passSerial, String passNumber);
    Optional<User> findByPassSerialAndPassNumber(String passSerial, String passNumber);
}
