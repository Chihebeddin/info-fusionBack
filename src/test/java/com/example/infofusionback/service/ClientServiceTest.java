package com.example.infofusionback.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.infofusionback.entity.Client;

@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
	protected ClientService service;
	
	protected long clientId;
	
	@Test
	public void testGetClientById() {
		Client client = service.getClientById(1);
		
		assertThat(client).isNotNull();
		assertThat(client.getId()).isEqualTo(1);
		assertThat(client.getEmail()).isEqualTo("toto@gmail.com");
		assertThat(client.getFirstName()).isEqualTo("toto");
		assertThat(client.getLastName()).isEqualTo("yoyo");
		
	}
	
	@Test
	public void testGetClientByIdNotOK() {
		Client client = service.getClientById(50);
		
		assertThat(client).isNull();
	}
	
	@Test
	public void testSaveClient() {
		Client client = new Client("john.doe@email.com", "test1234", LocalDateTime.now(), "ROLE_CLIENT");
		client.setFirstName("john");
		client.setLastName("doe");
		
		Client newClient = service.saveClient(client);
		this.clientId = newClient.getId();
		
		assertThat(newClient).isEqualTo(client);
		assertThat(newClient.getFirstName()).isEqualTo("john");
		assertThat(newClient.getLastName()).isEqualTo("doe");
		
	}
	
	@Test
	public void testDeleteClient() {
		assertThat(service.getClientById(clientId)).isNotNull();
		
		service.deleteClient(this.clientId);
		assertThat(service.getClientById(this.clientId)).isNull();
			
	}

}
