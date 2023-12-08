package com.example.infofusionback.service;

import java.util.List;

import com.example.infofusionback.entity.Client;

public interface ClientService {
	
	
	Client saveClient(Client client);
	
	Client getClientById(long id);
	
	List<Client> getAllClients();
	
	void deleteClient(long id);
}
