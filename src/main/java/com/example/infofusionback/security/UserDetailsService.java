package com.example.infofusionback.security;

import com.example.infofusionback.entity.BO.UserBO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.infofusionback.service.UserService;

import java.util.Optional;

public class UserDetailsService implements UserDetailsServiceInterface {


    private UserService userService;

    public UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public static UserDetailsService create(UserService userService) {
        return new UserDetailsService(userService);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBO> user = userService.findUserByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), user.get().getAuthorities());
        //return user.get();
    }
}