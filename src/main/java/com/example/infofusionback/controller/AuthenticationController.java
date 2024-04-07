package com.example.infofusionback.controller;

import com.example.infofusionback.MessageSingleton;
import com.example.infofusionback.entity.*;
import com.example.infofusionback.entity.BO.UserBO;

import java.lang.String;

import com.example.infofusionback.entity.dto.UserDTO;
import com.example.infofusionback.playload.request.ShopSignupRequest;
import com.example.infofusionback.repository.ShopTypeRepository;
import com.example.infofusionback.security.UserDetailsServiceInterface;
import com.example.infofusionback.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
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
import org.springframework.web.multipart.MultipartFile;

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
	private ShopTypeRepository shopTypeRepository;

	private MessageSingleton msgGenerator = MessageSingleton.getInstance();



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
			return ResponseEntity.badRequest().body("Adresse mail déjà utilisée");
		}

		if (!clientSignupRequest.getPassword().equals(clientSignupRequest.getConfirmPassword())) {
			return ResponseEntity.badRequest().body("Les mots de passe saisis ne correspondent pas");
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


		// Enregistrez le FidelityCard associé au client
		fidelityCard = fs.saveFidelityCard(fidelityCard, client);
		try{
			emailService.sendMailWithAttachment(clientSignupRequest.getEmail().toString(),
					this.msgGenerator.welcomeClient(clientSignupRequest.getFirstName()),
					this.msgGenerator.welcomeSubject());
		}catch (Exception e){
			System.out.println(e.toString());
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully !"));
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
	public ResponseEntity<?> register(@RequestParam String name,
			@RequestParam String location,
			@RequestParam String phone,
			@RequestParam String openingTime,
			@RequestParam String closingTime,
			@RequestParam MultipartFile image,
			@RequestParam String email,
			@RequestParam double longitude,
			@RequestParam double latitude,
			@RequestParam Set<String> ShopType,
			@RequestParam String password,
			@RequestParam String confirmPassword) throws Exception {
		if (userService.findUserByEmail(email).isPresent()) {
			return ResponseEntity.badRequest().body("Adresse mail déjà utilisée");
		}

		if (!password.equals(confirmPassword)) {
			return ResponseEntity.badRequest().body("Les mots de passe saisis ne correspondent pas");
		}

		Shop shop = new Shop();

		Set<ShopType> types = new HashSet<>();

		addShopType(ShopType, types);

		shop.setEmail(email);
		shop.setName(name);
		shop.setOpeningTime(openingTime);
		shop.setClosingTime(closingTime);
		shop.setD(LocalDateTime.now());
		shop.setLocation(location);
		shop.setPhone(phone);
		shop.setRole("ROLE_SHOP");
		shop.setImage(image.getBytes());
		shop.setLatitude(latitude);
		shop.setLongitude(longitude);
		shop.setShopType(types);
		shop.setPassword(passwordEncoder.encode(password));

		shop = ss.saveShop(shop);

		try{
			emailService.sendMailWithAttachment(email.toString(),
					this.msgGenerator.welcomeShop(),
					this.msgGenerator.welcomeSubject());
		}catch (Exception e){
			System.out.println(e.toString());

		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	private void addShopType(Set<String> ShopType, Set<ShopType> types) {
		if (ShopType == null) {
			ShopType shopType = shopTypeRepository.findByType(EShopType.Boucherie)
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
					ShopType SecRole = shopTypeRepository.findByType(EShopType.Boulangerie)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					types.add(SecRole);

					break;
				case "Poissonnerie":
					ShopType ptype = shopTypeRepository.findByType(EShopType.Poissonnerie)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					types.add(ptype);

					break;
				case "Fromagerie":
					ShopType ftype = shopTypeRepository.findByType(EShopType.Fromagerie)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					types.add(ftype);

					break;
				case "GrandeSurface":
					ShopType gtype = shopTypeRepository.findByType(EShopType.Grande_Surface)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					types.add(gtype);

					break;
				default:
					ShopType userRole = shopTypeRepository.findByType(EShopType.Boucherie)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					types.add(userRole);
				}
			});
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@SecurityRequirement(name = "JWT")
	public ResponseEntity<?> me(Authentication authentication) {
		Optional<UserBO> user = userService.findUserByEmail(authentication.getName());
		return user.map(value -> ResponseEntity.ok(new UserDTO(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
