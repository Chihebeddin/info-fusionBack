package com.example.infofusionback.controller;

import com.example.infofusionback.entity.BO.UserBO;
import com.example.infofusionback.entity.User;
import com.example.infofusionback.entity.dto.UserDTO;
import com.example.infofusionback.security.UserDetailsServiceInterface;
import com.example.infofusionback.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.infofusionback.entity.Client;


import com.example.infofusionback.playload.request.LoginRequest;
import com.example.infofusionback.playload.request.ClientSignupRequest;
import com.example.infofusionback.playload.response.MessageResponse;
import com.example.infofusionback.security.jwt.JwtResponse;
import com.example.infofusionback.security.jwt.JwtTokenUtil;
import com.example.infofusionback.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@AllArgsConstructor
@Tag(name = "Authentication", description = "The authentication API")
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceInterface userDetailsService;
    
  private ClientService cs;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/SignInClient", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String username = userDetails.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    }

    @RequestMapping(value = "/SignUpClient", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody ClientSignupRequest clientSignupRequest) throws Exception {
        if (userService.findUserByEmail(clientSignupRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        if (!clientSignupRequest.getPassword().equals(clientSignupRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        Client client = new Client();
        client.setEmail(clientSignupRequest.getEmail());
        client.setRole(clientSignupRequest.getRole());
        client.setPassword(passwordEncoder.encode(clientSignupRequest.getPassword()));
        client.setFirstName(clientSignupRequest.getFirstName());
        client.setLastName(clientSignupRequest.getLastName());
        client.setD(LocalDateTime.now());
        client.setBirthdate(clientSignupRequest.getBirthdate());
        client.setPhone(clientSignupRequest.getPhone());
        client = cs.saveClient(client);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> me(Authentication authentication) {
        Optional<UserBO> user = userService.findUserByEmail(authentication.getName());
        return user.map(value -> ResponseEntity.ok(new UserDTO(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}

