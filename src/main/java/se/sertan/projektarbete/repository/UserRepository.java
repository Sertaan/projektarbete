package se.sertan.projektarbete.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sertan.projektarbete.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);
}
