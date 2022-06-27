package it.city.edu.repository;

import it.city.edu.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  ContactRepository extends JpaRepository<Contact, UUID> {
    boolean existsByEmailEqualsIgnoreCase(String email);
    boolean existsByEmailEqualsIgnoreCaseAndIdNot(String email, UUID id);
}
