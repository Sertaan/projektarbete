package se.sertan.projektarbete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sertan.projektarbete.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
