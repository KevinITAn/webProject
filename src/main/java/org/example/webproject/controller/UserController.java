package org.example.webproject.controller;

import org.example.webproject.model.User;
import org.example.webproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, String passwordConfirm, Model model) {
        // Check se lo username esiste già
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("usernameError", "Username già esistente");
            return "register";
        }

        // Validazioni esistenti
        if (user.getFirstName().isEmpty()) {
            model.addAttribute("firstNameError", "Il nome non può essere vuoto");
            return "register";
        }
        if (user.getLastName().isEmpty()) {
            model.addAttribute("lastNameError", "Il cognome non può essere vuoto");
            return "register";
        }
        if (user.getUsername().length() < 5) {
            model.addAttribute("usernameError", "L'username deve essere almeno di 5 caratteri");
            return "register";
        }
        if (user.getPassword().length() < 8) {
            model.addAttribute("passwordError", "La password deve essere almeno di 8 caratteri");
            return "register";
        }
        if (!user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Le password non corrispondono");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userService.createUser(user);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", model.containsAttribute("error") ? "Credenziali non valide" : null);
        model.addAttribute("success", model.containsAttribute("success") ? "Registrazione completata!" : null);
        return "login";
    }
}