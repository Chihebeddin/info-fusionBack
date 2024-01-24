package com.example.infofusionback;

import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.EShopType;
import com.example.infofusionback.entity.Shop;
import com.example.infofusionback.entity.ShopType;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.ShopService;
import com.example.infofusionback.service.ShopTypeService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class InfoFusionBackApplication {
	@Autowired
	private ClientService cs;
	@Autowired
	private ShopService ss;
	public static void main(String[] args) {
		SpringApplication.run(InfoFusionBackApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ShopTypeService shopTypeService) {
		return args -> {

			if (!shopTypeService.existsByType(EShopType.Boucher)) {
				shopTypeService.saveShopType(new ShopType(null, EShopType.Boulanger));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Epicerie));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Fleuriste));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Boucher));

			}
		};
	}

	/*@Bean
	public CommandLineRunner generateData() {
		return args -> {
			generateClientData();
			generateShopData();
		};
	}

	private void generateClientData() {
		Faker faker = new Faker();

		for (int i = 1; i <= 100; i++) {
			Client client = new Client();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			client.setEmail(firstName+lastName + i + "@example.com");
			client.setPassword(faker.internet().password());
			client.setD(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			client.setRole("ROLE_CLIENT");
			client.setFirstName(firstName);
			client.setLastName(lastName);
			client.setPhone(faker.phoneNumber().cellPhone());
			client.setBirthdate(faker.date().birthday());
			cs.saveClient(client);
		}
	}
	private void generateShopData() {
		Faker faker = new Faker();

		for (int i = 1; i <= 30; i++) {
			Shop shop = new Shop();
			shop.setEmail(faker.internet().emailAddress());
			shop.setPassword(faker.internet().password());
			shop.setD(LocalDateTime.now());
			shop.setRole("SHOP");
			shop.setName(faker.company().name());
			shop.setLocation(faker.address().fullAddress());
			shop.setPhone(faker.phoneNumber().cellPhone());
			LocalTime openingTime = LocalTime.of(faker.number().numberBetween(6, 10), 0);
			LocalTime closingTime = LocalTime.of(faker.number().numberBetween(16, 22), 0);
			shop.setOpeningTime(openingTime.toString());
			shop.setClosingTime(closingTime.toString());
			ss.saveShop(shop);
		}
	}*/

}
