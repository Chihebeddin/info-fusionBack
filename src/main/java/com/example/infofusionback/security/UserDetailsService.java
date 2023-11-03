package com.example.infofusionback.security;

import com.example.infofusionback.entity.BO.UserBO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.infofusionback.entity.User;
import com.example.infofusionback.service.UserService;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailsService implements UserDetailsServiceInterface {


	public static UserDetailsService create(UserService userService) {
        return new UserDetailsService(userService);
    }

    private UserService userService;

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
