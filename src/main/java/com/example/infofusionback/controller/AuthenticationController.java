package com.example.infofusionback.controller;

import com.example.infofusionback.entity.BO.UserBO;
import com.example.infofusionback.entity.Shop;
import java.lang.String;

import com.example.infofusionback.entity.dto.UserDTO;
import com.example.infofusionback.playload.request.ShopSignupRequest;
import com.example.infofusionback.security.UserDetailsServiceInterface;
import com.example.infofusionback.service.EmailSenderService;
import com.example.infofusionback.service.ShopService;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired

    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserDetailsServiceInterface userDetailsService;

    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private ClientService cs;

    @Autowired
    private ShopService ss;

    private String name;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;


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
        client.setRole("ROLE_CLIENT");
        client.setPhone(clientSignupRequest.getPhone());
        client = cs.saveClient(client);
        try{
            name="Hello "+clientSignupRequest.getLastName();


            System.out.println(name);
            emailService.sendMailWithAttachment(clientSignupRequest.getEmail().toString(),
                    name.toString(),
                    "Account Infos");
        }catch (Exception e){
            System.out.println(e.toString());

        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/current")
    public Optional<UserBO> getUser(Authentication authentication) {
        System.out.println("OOOOOOOO "+authentication.getName());
        Optional<UserBO> user = userService.findUserByEmail(authentication.getName());
        return user;
    }
    @RequestMapping(value = "/SignUpShop", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody ShopSignupRequest shopSignupRequest) throws Exception {
        if (userService.findUserByEmail(shopSignupRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        if (!shopSignupRequest.getPassword().equals(shopSignupRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        Shop shop = new Shop();
        shop.setEmail(shopSignupRequest.getEmail());
        shop.setRole(shopSignupRequest.getRole());
        shop.setPassword(passwordEncoder.encode(shopSignupRequest.getPassword()));
        shop.setName(shopSignupRequest.getName());
        shop.setOpeningTime(shopSignupRequest.getOpeningTime());
        shop.setClosingTime(shopSignupRequest.getClosingTime());
        shop.setD(LocalDateTime.now());
        shop.setLocation(shopSignupRequest.getLocation());
        shop.setPhone(shopSignupRequest.getPhone());
        shop.setRole("ROLE_SHOP");
        shop = ss.saveShop(shop);

        try{
            name="Hello "+shopSignupRequest.getName();


            System.out.println(name);
            emailService.sendMailWithAttachment(shopSignupRequest.getEmail().toString(),
                    name.toString(),
                    "Account Infos");
        }catch (Exception e){
            System.out.println(e.toString());

        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> me(Authentication authentication) {
        Optional<UserBO> user = userService.findUserByEmail(authentication.getName());
        return user.map(value -> ResponseEntity.ok(new UserDTO(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}

