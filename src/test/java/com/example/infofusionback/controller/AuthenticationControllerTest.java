package com.example.infofusionback.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
	
	@Autowired
	protected AuthenticationController controller;
	
	@Autowired
	protected MockMvc mockMVC;
	
	protected String url = "/api/auth";
	
	@Test
	public void testCreateAuthenticationToken() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(post(url +"/SignInClient")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"toto@gmail.com\", \"password\": \"hello\"}"))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		
		String authUser = mvcResult.getResponse().getContentAsString();
		
		assertThat(authUser).isNotNull();
	}
	
	@Test
	public void testRegisterClient() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(post(url +"/SignUpClient")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"test@email.com\", \"password\": \"hello\", \"confirmPassword\": \"hello\","
                		+ "\"birthdate\": \"1999-12-24\", \"firstName\": \"client\", \"lastName\": \"test\","
                		+ "\"phone\": \"0612345789\"}"))
				.andReturn();
		
		String registerMsg = mvcResult.getResponse().getContentAsString();
		
		assertThat(registerMsg).isNotNull();
		assertThat(registerMsg).contains("User registered successfully!");
	}
	
	@Test
	public void testRegisterShop() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(post(url +"/SignUpShop")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"ashop@gmail.com\", \"password\": \"shop1234\", \"confirmPassword\": \"shop1234\",\r\n"
                		+ "\"name\": \"A shop\", \"openingTime\": \"10h\", \"closingTime\": \"21h\",\r\n"
                		+ "\"location\": \"123 Avenue Address\", \"phone\": \"0213456789\"}"))
				.andReturn();
		
		String registerMsg = mvcResult.getResponse().getContentAsString();
		
		assertThat(registerMsg).isNotNull();
		assertThat(registerMsg).contains("User registered successfully!");
	}
	
	@Test
	public void testRegisterNotOK() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(post(url +"/SignUpShop")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"ashop@gmail.com\", \"password\": \"shop1234\", \"confirmPassword\": \"shop1234\",\r\n"
                		+ "\"name\": \"Another shop\", \"openingTime\": \"8h\", \"closingTime\": \"20h\",\r\n"
                		+ "\"location\": \"Centre commercial Euralille\", \"phone\": \"0213456789\"}"))
				.andReturn();
		
		String registerMsg = mvcResult.getResponse().getContentAsString();
		
		assertThat(registerMsg).isNotNull();
		assertThat(registerMsg).contains("User already exists");
	}

}
