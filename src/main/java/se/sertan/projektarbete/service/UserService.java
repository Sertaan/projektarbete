package se.sertan.projektarbete.service;

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
