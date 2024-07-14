package se.sertan.projektarbete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.sertan.projektarbete.entity.AppUser;
import se.sertan.projektarbete.service.UserService;
import se.sertan.projektarbete.web.UserDTO;

@Controller
@RequestMapping("/")
public class ProjektarbeteController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ProjektarbeteController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/")
  public String loggedIn() {
    return "loggedIn";
  }

  @GetMapping("/register")
  public String registerForm(Model model) {
    model.addAttribute("user", new UserDTO());
    return "register";
  }

  @PostMapping("/register")
  public String registerSubmit(AppUser user, RedirectAttributes redirectAttributes) {
    userService.registerUser(user);
    redirectAttributes.addAttribute("success", true);
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  @GetMapping("/welcome")
  public String showWelcomePage() {
    return "welcome";
  }

  @GetMapping("/admin")
  public String adminPage() {
    return "admin";
  }

  @GetMapping("/logged-out")
  public String loggedOut() {
    return "loggedOut";
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return "accessDenied";
  }

  @PostMapping("/delete")
  public String deleteUser(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
    userService.deleteUser(userId);
    redirectAttributes.addFlashAttribute("message", "User deleted successfully.");
    return "redirect:/users";
  }

  @GetMapping("/users")
  public String listUsers(Model model) {
    model.addAttribute("users", userService.findAllUsers());
    return "users";
  }
}