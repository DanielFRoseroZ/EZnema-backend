package com.eznema.vb_test.user.controller;

import com.eznema.vb_test.user.model.User;
import com.eznema.vb_test.user.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<User> getUsers() {
        return userDetailsService.getAllUsersWithAuthority();
    }
}
