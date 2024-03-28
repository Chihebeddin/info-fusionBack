package com.example.infofusionback.controller;

import java.util.List;
import java.util.Optional;

import com.example.infofusionback.entity.BO.UserBO;
import com.example.infofusionback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private final ClientService clientService;
    @Autowired
    private final UserService userService;

    @Autowired
    public ClientController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @GetMapping(value ="/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }



    @GetMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        client.setId(id);
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}