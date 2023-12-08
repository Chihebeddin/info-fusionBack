package com.example.infofusionback.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
	
	@Autowired
	protected ClientController controller;
	
	@Autowired
	protected MockMvc mockMVC;
	
	protected String url = "/clients";
	
	@Test
	public void testGetAllClients() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(get(url))
				.andReturn();
		
		String clients = mvcResult.getResponse().getContentAsString();
		
		assertThat(clients).isNotNull();
		
		assertThat(clients).contains("toto");
	}
	
	@Test
	public void testGetClientById() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(get(url +"/1"))
				.andReturn();
		
		String client = mvcResult.getResponse().getContentAsString();
		
		assertThat(client).isNotNull();
		
		assertThat(client).contains("toto", "yoyo", "toto@gmail.com");
	}

}
