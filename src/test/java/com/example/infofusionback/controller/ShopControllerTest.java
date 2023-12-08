package com.example.infofusionback.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {
	
	@Autowired
	protected ShopController controller;
	
	@Autowired
	protected MockMvc mockMVC;
	
	protected String url = "/shops";
	
	@Test
	public void testGetAllShops() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(get(url))
				.andReturn();
		
		String shops = mvcResult.getResponse().getContentAsString();
		
		assertThat(shops).isNotNull();
		
		assertThat(shops).contains("Le fournil");
	}
	
	@Test
	public void testGetShopById() throws Exception {
		MvcResult mvcResult = this.mockMVC.perform(get(url +"/2"))
				.andReturn();
		
		String shop = mvcResult.getResponse().getContentAsString();
		
		assertThat(shop).isNotNull();
		
		assertThat(shop).contains("Le fournil", "lefournil@gmail.com", "100 Boulevard Jean Lebas");
	}

}
