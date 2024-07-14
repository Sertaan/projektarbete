package se.sertan.projektarbete.service;

/*
Info om UserService, @Service annotationen är lite svår att förklara man kan säga att denna class "servar" Users

Vi börjar med att injecta metoder från UserRepository med hjälp av @Autowired
Vi gör samma med passwordEncoder.
Dessa två är våra "dependencies" man kan säga att det är metoder som vår class är "dependent" av för att fungera som den ska.

Vissa metoder har @Transactional annotationen detta betyder att vi ser till så att allt i databasen faktiskt uppdateras om något failar så failas allt så att inget konstigt händer
i våran databas.

Eftersom vi använde oss av extends JpaRepository i Vår Repository har vi tillgång till metoder som .save .delete mm

metoderna I denna class är ganska självförklarande, registerUser  så sparar vi ser en ny user i våran databas, deleteUser tar bort en användare, updateUserPassword upadterar vi
lösenordet, vi kollar först om användaren finns i databasen förståss.


 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import se.sertan.projektarbete.entity.AppUser;
import se.sertan.projektarbete.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public AppUser registerUser(AppUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional
  public AppUser updateUserPassword(String username, String newPassword) throws UsernameNotFoundException {
    Optional<AppUser> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      AppUser user = userOptional.get();
      user.setPassword(passwordEncoder.encode(newPassword));
      return userRepository.save(user);
    } else {
      throw new UsernameNotFoundException("User not found");
    }
  }
  public List<AppUser> findAllUsers() {
    return userRepository.findAll();
  }

  public AppUser findUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }
}
