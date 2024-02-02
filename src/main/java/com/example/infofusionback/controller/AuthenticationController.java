package com.example.infofusionback.controller;

import com.example.infofusionback.entity.*;
import com.example.infofusionback.entity.BO.UserBO;

import java.io.ByteArrayOutputStream;
import java.lang.String;

import com.example.infofusionback.entity.dto.UserDTO;
import com.example.infofusionback.playload.request.ShopSignupRequest;
import com.example.infofusionback.repository.ShopTypeRepository;
import com.example.infofusionback.security.UserDetailsServiceInterface;
import com.example.infofusionback.service.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.example.infofusionback.playload.request.LoginRequest;
import com.example.infofusionback.playload.request.ClientSignupRequest;
import com.example.infofusionback.playload.response.MessageResponse;
import com.example.infofusionback.security.jwt.JwtResponse;
import com.example.infofusionback.security.jwt.JwtTokenUtil;

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
    @Autowired
    private FidelityCardService fs;

    private String name;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;

    @Autowired
    private ShopTypeRepository shopTypeRepository;

    @RequestMapping(value = "/SignIn", method = RequestMethod.POST)
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

        FidelityCard fidelityCard = new FidelityCard();
        fidelityCard.setNbPoints(0);
        fidelityCard.setSolde(0.0);
        fidelityCard.setDatePoints(LocalDateTime.now());

        // Enregistrez le FidelityCard associé au client
        fidelityCard = fs.saveFidelityCard(fidelityCard);
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

    @GetMapping("/fidelitycard")
    public List<FidelityCard> getFidelityCards() {
        return fs.getAllFidelityCards();
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

        Set<String> ShopType = shopSignupRequest.getShopType();
        Set<ShopType> types = new HashSet<>();

        if (ShopType == null) {
            System.out.println("kjlkjljhjhjhohohljhl");
            ShopType shopType = shopTypeRepository.findByType(EShopType.Boucher)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            types.add(shopType);
        } else {
            ShopType.forEach(type -> {
                switch (type) {
                    case "Fleuriste":
                        ShopType adminRole = shopTypeRepository.findByType(EShopType.Fleuriste)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        types.add(adminRole);

                        break;
                    case "Epicerie":
                        ShopType modRole = shopTypeRepository.findByType(EShopType.Epicerie)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        types.add(modRole);

                        break;
                    case "Boulanger":
                        ShopType SecRole = shopTypeRepository.findByType(EShopType.Boulanger)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        types.add(SecRole);

                        break;
                    default:
                        ShopType userRole = shopTypeRepository.findByType(EShopType.Boucher)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        types.add(userRole);
                }
            });
        }

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
        shop.setShopType(types);
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
