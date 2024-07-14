package se.sertan.projektarbete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sertan.projektarbete.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
