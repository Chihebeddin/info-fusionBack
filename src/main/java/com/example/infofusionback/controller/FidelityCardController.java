package com.example.infofusionback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.FidelityCard;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.FidelityCardService;

@RestController
@RequestMapping("/fidelitycards")
public class FidelityCardController {
	
	@Autowired
	protected ClientService cs;
	
	@Autowired
	protected FidelityCardService fs;
	
	@GetMapping("/{id}")
	public FidelityCard orderById(@PathVariable long id) {
		return fs.getById(id);
	}

	@GetMapping("/client/{id}")
	public FidelityCard getByClientId(@PathVariable long id) {
		return fs.findByUser(id);
	}

	@GetMapping()
	public List<FidelityCard> products() {
		return fs.getAllFidelityCards();
	}
	
	@PostMapping("/create")
	public FidelityCard createFidelityCard(@RequestParam(value="client")long clientId) {
		Client c = cs.getClientById(clientId);
		
		FidelityCard fc = new FidelityCard();
		
		return fs.saveFidelityCard(fc, c);
	}
	
	@PutMapping("/addPoints")
	public FidelityCard addPointsToCard(@RequestParam(value="client")long clientId, @RequestParam(value="amount")double amount) {
		return fs.addPoints(clientId, amount, -amount);
	}
	
	@PutMapping("/refund")
	public ResponseEntity<?> updateFidelityCard(@RequestParam(value="client")long clientId, @RequestParam(value="solde")double amount) {
		try {
			FidelityCard fc = fs.refundCard(clientId, amount);
			return ResponseEntity.ok(fc);
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
