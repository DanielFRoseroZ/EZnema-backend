package com.eznema.vb_test.user.service;

import com.eznema.vb_test.user.model.User;
import com.eznema.vb_test.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz "UserDetailsService" de Spring Security. Se encarga de cargar los detalles de un usario desde UserRepository
 * */

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> getAllUsersWithAuthority() {
        List<User> allUsers = userRepository.findAll();
        return (List<User>) allUsers.stream()
                .filter(user -> user.getAuthorities().contains(new SimpleGrantedAuthority("USER")))
                .collect(Collectors.toList());
    }
}
