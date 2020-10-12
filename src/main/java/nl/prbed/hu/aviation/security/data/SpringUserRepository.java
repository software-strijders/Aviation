package nl.prbed.hu.aviation.security.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
