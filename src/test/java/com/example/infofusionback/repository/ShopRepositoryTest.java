package com.example.infofusionback.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.infofusionback.entity.Shop;


@SpringBootTest
public class ShopRepositoryTest {
	
	@Autowired
	protected ShopRepository repo;
	
	@Test
	public void testFindAll() {
		List<Shop> shops = repo.findAll();
		
		assertThat(shops).isNotEmpty();
	}

}
