package com.example.infofusionback.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.infofusionback.entity.Client;

@SpringBootTest
public class ClientRepositoryTest {
	
	@Autowired
	protected ClientRepository repo;
	
	@Test
	public void testFindAll() {
		List<Client> clients = repo.findAll();
		
		assertThat(clients).isNotEmpty();
	}

}
