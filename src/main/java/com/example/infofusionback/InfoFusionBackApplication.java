package com.example.infofusionback;

import com.example.infofusionback.entity.*;
import com.example.infofusionback.service.*;
import com.github.javafaker.Faker;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class InfoFusionBackApplication {

	@Autowired
	private ClientService cs;

	@Autowired
	private FidelityCardService fs;

	@Autowired
	private ShopService ss;

	@Autowired
	protected ProductService ps;

	@Autowired
	protected CategoryService cts;
	
	@Autowired
	protected OrderService os;

	public static void main(String[] args) {
		SpringApplication.run(InfoFusionBackApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ShopTypeService shopTypeService) {
		return args -> {

			if (!shopTypeService.existsByType(EShopType.Boucherie)) {
				shopTypeService.saveShopType(new ShopType(null, EShopType.Boulangerie));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Epicerie));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Fleuriste));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Boucherie));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Fromagerie));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Grande_Surface));
				shopTypeService.saveShopType(new ShopType(null, EShopType.Poissonnerie));

			}
		};
	}

	@Bean
	public CommandLineRunner generateData() {
		return args -> {
			//generateClientData();
			// generateShopData();
			//generateCategorieData();
			//this.generateOrders();
		};
	}


	private void generateClientData() throws InterruptedException {
		Faker faker = new Faker(new Locale("fr"));
		
		for (int i = 1; i <= 100; i++) {
			Client client = new Client();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			client.setEmail(firstName+lastName + i + "@gmail.com");
			client.setPassword(faker.internet().password());
			client.setD(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			client.setRole("ROLE_CLIENT");
			client.setFirstName(firstName);
			client.setLastName(lastName);
			client.setPhone(faker.phoneNumber().cellPhone());
			client.setBirthdate(faker.date().birthday());
			cs.saveClient(client);

			// Convertir LocalDateTime en Date
			Date debutDate = Date.from(LocalDateTime.of(2023, Month.DECEMBER, 1, 0, 0).atZone(ZoneId.systemDefault()).toInstant());
			Date finDate = Date.from(LocalDateTime.of(2024, Month.MARCH, 31, 23, 59).atZone(ZoneId.systemDefault()).toInstant());

			// Générer une date aléatoire entre les bornes temporelles spécifiées
			Date dateAleatoire = faker.date().between(debutDate, finDate);

			// Convertir Date en LocalDateTime
			LocalDateTime dateAleatoireLocalDateTime = dateAleatoire.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			FidelityCard fidelityCard = new FidelityCard();
			int nbPoints = faker.number().numberBetween(0, 50);
			double solde = faker.number().randomDouble(2, 0, 1000);
			fidelityCard.setNbPoints(nbPoints);
			fidelityCard.setSolde(solde);
			fidelityCard.setDatePoints(dateAleatoireLocalDateTime);

			// Enregistrez le FidelityCard associé au client
			fs.saveFidelityCard(fidelityCard, client);
		}
	}

	@Transactional
	private void generateShopData() throws CsvValidationException, IOException {
		Faker faker = new Faker(new Locale("fr"));
		Random random = new Random();
		BufferedReader reader = new BufferedReader(new FileReader("adresses.csv"));
		String nextRecord;

		for (int i = 0; i <= 50; i++) {
			Shop shop = new Shop();
			Set<ShopType> shopTypes = new HashSet<>();
			// Randomly choose one or more types for each shop
			for (int j = 0; j < random.nextInt(4) + 1; j++) {
				EShopType type = EShopType.values()[random.nextInt(EShopType.values().length)];
				ShopType shopType = new ShopType();
				shopType.setType(type);
				shopTypes.add(shopType);
			}
			shop.setShopType(shopTypes);
			shop.setEmail(faker.internet().emailAddress());
			shop.setPassword(faker.internet().password());
			shop.setD(LocalDateTime.now());
			shop.setRole("ROLE_SHOP");
			shop.setName(faker.company().name());
			
			String[] line = reader.readLine().split(";");
			shop.setLocation(line[2] +" "+ line[4] +", "+ line[5] +", "+ line[7]);
			shop.setLongitude(Double.parseDouble(line[12]));
			shop.setLatitude(Double.parseDouble(line[13]));
			
			shop.setPhone(faker.phoneNumber().cellPhone());
			LocalTime openingTime = LocalTime.of(faker.number().numberBetween(6, 10), 0);
			LocalTime closingTime = LocalTime.of(faker.number().numberBetween(16, 22), 0);
			shop.setOpeningTime(openingTime.toString());
			shop.setClosingTime(closingTime.toString());

			shop.setShopType(shopTypes);
			
			ss.saveShop(shop);
			
			this.generateProductData(shop);
			

		}
	}


	private void generateCategorieData() throws IOException {
		
		Category fruits = new Category("Fruits");
		Category vegetables = new Category("Légumes");
		Category milk = new Category("Produits laitiers");
		Category meat = new Category("Viandes");
		Category chick = new Category("Volailles");
		Category spices = new Category("Condiments et épices");
		Category corn = new Category("Produits céréaliers");
		Category ch = new Category("Charcuterie");
		Category frozen = new Category("Produits surgelés");
		Category fish = new Category("Poissons");
		Category seafood = new Category("Fruits de mer");

		cts.addCategory(fruits);
		cts.addCategory(vegetables);
		cts.addCategory(milk);
		cts.addCategory(meat);
		cts.addCategory(chick);
		cts.addCategory(spices);
		cts.addCategory(corn);
		cts.addCategory(ch);
		cts.addCategory(frozen);
		cts.addCategory(fish);
		cts.addCategory(seafood);
		
		BufferedReader reader = new BufferedReader(new FileReader("categories.csv"));
		String nextRecord;
		while ((nextRecord = reader.readLine()) != null) {
			cts.addCategory(new Category(nextRecord));
		}
	}
	
	@Transactional
	private void generateProductData(Shop shop) {
		Faker faker = new Faker(new Locale("fr"));
		Category ct = cts.getCategoryByName("Boissons alcoolisées");

		for (int i = 0; i <= 10; i++) {
			Product prd = new Product();

			prd.setName(faker.beer().name());
			prd.setQuantity(faker.number().numberBetween(10, 100));
			prd.setSafetyStock(prd.getQuantity() - 10);
			prd.setPrice(faker.number().randomDouble(2, 2, 50));
			prd.setShop(shop);

			this.ps.addProduct(prd, shop, ct);
		}

		ct = cts.getCategoryByName("Fruits");
		for (int i = 0; i <= 10; i++) {
			Product prd = new Product();

			prd.setName(faker.food().fruit());
			prd.setQuantity(faker.number().numberBetween(30, 100));
			prd.setSafetyStock(prd.getQuantity() - 30);
			prd.setPrice(faker.number().randomDouble(2, 0, 3));
			prd.setShop(shop);

			this.ps.addProduct(prd, shop, ct);
		}

		ct = cts.getCategoryByName("Légumes");
		for (int i = 0; i <= 10; i++) {
			Product prd = new Product();

			prd.setName(faker.food().vegetable());
			prd.setQuantity(faker.number().numberBetween(50, 150));
			prd.setSafetyStock(prd.getQuantity() - 50);
			prd.setPrice(faker.number().randomDouble(2, 0, 2));
			prd.setShop(shop);

			this.ps.addProduct(prd, shop, ct);
		}

		ct = cts.getCategoryByName("Condiments et épices");
		for (int i = 0; i <= 20; i++) {
			Product prd = new Product();

			prd.setName(faker.food().spice());
			prd.setQuantity(faker.number().numberBetween(10, 60));
			prd.setSafetyStock(prd.getQuantity() - 10);
			prd.setPrice(faker.number().randomDouble(2, 1, 5));
			prd.setShop(shop);

			this.ps.addProduct(prd, shop, ct);
		}
	}
	
	private void generateOrders() {
		List<Client> clients = cs.getAllClients();
		for (int i = 0; i < 10; i++) {
			OrderEntity o = new OrderEntity();
			Client c = this.getRandomShop(clients);
			
			o.setPaymentOption("Cash");
			o.setStatus("Terminée");
			
			LocalDateTime startDate = LocalDateTime.of(2023, Month.DECEMBER, 1, 0, 0);
	        LocalDateTime endDate = LocalDateTime.of(2024, Month.MARCH, 31, 23, 59);

	        long totalSeconds = endDate.toEpochSecond(java.time.ZoneOffset.UTC) - startDate.toEpochSecond(java.time.ZoneOffset.UTC);
	        long randomSeconds = ThreadLocalRandom.current().nextLong(totalSeconds + 1);
	        LocalDateTime randomDateTime = startDate.plusSeconds(randomSeconds);
	        
	        o.setValidationDate(randomDateTime);
	        o.setCollectDate(randomDateTime);
	        
			os.addOrder(o, c);
		}
		
		for (int i = 0; i < 10; i++) {
			OrderEntity o = new OrderEntity();
			Client c = this.getRandomShop(clients);
			
			o.setPaymentOption("CreditCard");
			o.setStatus("Terminée");
			
			LocalDateTime startDate = LocalDateTime.of(2023, Month.DECEMBER, 1, 0, 0);
	        LocalDateTime endDate = LocalDateTime.of(2024, Month.MARCH, 31, 23, 59);

	        long totalSeconds = endDate.toEpochSecond(java.time.ZoneOffset.UTC) - startDate.toEpochSecond(java.time.ZoneOffset.UTC);
	        long randomSeconds = ThreadLocalRandom.current().nextLong(totalSeconds + 1);
	        LocalDateTime randomDateTime = startDate.plusSeconds(randomSeconds);
	        
	        o.setValidationDate(randomDateTime);
	        o.setCollectDate(randomDateTime);
	        
			os.addOrder(o, c);
		}
		
		for (int i = 0; i < 10; i++) {
			OrderEntity o = new OrderEntity();
			Client c = this.getRandomShop(clients);
			
			o.setPaymentOption("CreditCard");
			o.setStatus("Rejetée");
			
			LocalDateTime startDate = LocalDateTime.of(2023, Month.DECEMBER, 1, 0, 0);
	        LocalDateTime endDate = LocalDateTime.of(2024, Month.MARCH, 31, 23, 59);

	        long totalSeconds = endDate.toEpochSecond(java.time.ZoneOffset.UTC) - startDate.toEpochSecond(java.time.ZoneOffset.UTC);
	        long randomSeconds = ThreadLocalRandom.current().nextLong(totalSeconds + 1);
	        LocalDateTime randomDateTime = startDate.plusSeconds(randomSeconds);
	        
	        o.setValidationDate(randomDateTime);
	        
			os.addOrder(o, c);
		}
		
	}

	private Client getRandomShop(List<Client> shops) {
		int index = (int)Math.floor(Math.random() * ((shops.size()-1) - 0 + 1) + 0);
		return shops.get(index);
	}

}
