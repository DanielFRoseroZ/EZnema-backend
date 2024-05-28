package com.eznema.vb_test.service;

import com.eznema.vb_test.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
