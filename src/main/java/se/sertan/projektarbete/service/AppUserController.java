package se.sertan.projektarbete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sertan.projektarbete.repository.AppUserRepository;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    // Radera en användare
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (appUserRepository.existsById(id)) {
            appUserRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
