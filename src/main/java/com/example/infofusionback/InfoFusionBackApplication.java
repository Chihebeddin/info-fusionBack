package com.example.infofusionback;

import com.example.infofusionback.entity.Category;
import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.EShopType;
import com.example.infofusionback.entity.Product;
import com.example.infofusionback.entity.Shop;
import com.example.infofusionback.entity.ShopType;
import com.example.infofusionback.service.CategoryService;
import com.example.infofusionback.service.ClientService;
import com.example.infofusionback.service.ProductService;
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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class InfoFusionBackApplication {
	
	@Autowired
	private ClientService cs;
	
	@Autowired
	private ShopService ss;
	
	@Autowired
	protected ProductService ps;
	
	@Autowired
	protected CategoryService cts;
	
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

	@Bean
	public CommandLineRunner generateData() {
		return args -> {
			/*generateClientData();
			generateShopData();
			generateCategorieData();
			this.generateProductData();*/
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
			shop.setRole("ROLE_SHOP");
			shop.setName(faker.company().name());
			shop.setLocation(faker.address().fullAddress());
			shop.setPhone(faker.phoneNumber().cellPhone());
			LocalTime openingTime = LocalTime.of(faker.number().numberBetween(6, 10), 0);
			LocalTime closingTime = LocalTime.of(faker.number().numberBetween(16, 22), 0);
			shop.setOpeningTime(openingTime.toString());
			shop.setClosingTime(closingTime.toString());
			//shop.setShopType();
			ss.saveShop(shop);
		}
	}

	private void generateCategorieData() {

		Category fruits = new Category();
		fruits.setName("Fruits");

		Category vegetables = new Category();
		vegetables.setName("Vegetables");

		Category spices = new Category();
		spices.setName("Spices");

		Category beers = new Category();
		beers.setName("Beers");

		cts.addCategory(fruits);
		cts.addCategory(vegetables);
		cts.addCategory(spices);
		cts.addCategory(beers);
	}
	private void generateProductData() {
		Faker faker = new Faker(new Locale("fr", "FR")); 
		List<Shop> shops = ss.getAllShops();
		Category ct = cts.getCategoryById(4);
		
		for (int i = 0; i <= 10; i++) {
			Product prd = new Product();
			Shop shop = this.getRandomShop(shops);
			
			prd.setName(faker.beer().name());
			prd.setQuantity(faker.number().numberBetween(10, 100));
			prd.setPrice(faker.number().randomDouble(2, 2, 10));
			prd.setShop(shop);
			
			this.ps.addProduct(prd, shop, ct);
		}
		
		ct = cts.getCategoryById(1);
		for (int i = 0; i <= 10; i++) {
			Product prd = new Product();
			Shop shop = this.getRandomShop(shops);
			
			prd.setName(faker.food().fruit());
			prd.setQuantity(faker.number().numberBetween(30, 100));
			prd.setPrice(faker.number().randomDouble(2, 0, 3));
			prd.setShop(shop);
			
			this.ps.addProduct(prd, shop, ct);
		}
		
		ct = cts.getCategoryById(2);
		for (int i = 0; i <= 20; i++) {
			Product prd = new Product();
			Shop shop = this.getRandomShop(shops);
			
			prd.setName(faker.food().vegetable());
			prd.setQuantity(faker.number().numberBetween(50, 150));
			prd.setPrice(faker.number().randomDouble(2, 0, 2));
			prd.setShop(shop);
			
			this.ps.addProduct(prd, shop, ct);
		}
		
		ct = cts.getCategoryById(3);
		for (int i = 0; i <= 20; i++) {
			Product prd = new Product();
			Shop shop = this.getRandomShop(shops);
			
			prd.setName(faker.food().ingredient());
			prd.setQuantity(faker.number().numberBetween(10, 50));
			prd.setPrice(faker.number().randomDouble(2, 1, 10));
			prd.setShop(shop);
			
			this.ps.addProduct(prd, shop, ct);
		}
		
		ct = cts.getCategoryById(3);
		for (int i = 0; i <= 20; i++) {
			Product prd = new Product();
			Shop shop = this.getRandomShop(shops);
			
			prd.setName(faker.food().spice());
			prd.setQuantity(faker.number().numberBetween(10, 60));
			prd.setPrice(faker.number().randomDouble(2, 1, 5));
			prd.setShop(shop);
			
			this.ps.addProduct(prd, shop, ct);
		}
	}
	
	private Shop getRandomShop(List<Shop> shops) {
		int index = (int)Math.floor(Math.random() * ((shops.size()-1) - 0 + 1) + 0);
		return shops.get(index);
	}

}
