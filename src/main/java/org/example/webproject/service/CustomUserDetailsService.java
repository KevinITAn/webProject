package org.example.webproject.service;

import org.example.webproject.model.User;
import org.example.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));

        // Gestione del ruolo
        String role = user.getRole();
        if (role == null || role.isBlank()) {
            role = "ROLE_USER"; // Default role
        } else if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role.trim().toUpperCase(); // Assicurati che inizi con ROLE_
        }

        // Crea le autorità basate sul ruolo
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);

        // Restituisci UserDetails con i dati dell'utente
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), // Deve essere codificata (es. con BCrypt)
                true, // accountNonExpired
                true, // accountNonLocked
                true, // credentialsNonExpired
                true, // enabled
                authorities
        );
    }
}