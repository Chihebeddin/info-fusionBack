package com.example.infofusionback.service;

import java.util.List;
import java.util.Optional;

import com.example.infofusionback.entity.BO.UserBO;
import com.example.infofusionback.entity.Client;

public interface ClientService {
	 	Client saveClient(Client client);
	    Client getClientById(Long id);
	    List<Client> getAllClients();
	    void deleteClient(Long id);
		Client findClientByEmail(String email);
}
