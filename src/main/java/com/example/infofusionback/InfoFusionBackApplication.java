package com.example.infofusionback;

import com.example.infofusionback.entity.EShopType;
import com.example.infofusionback.entity.ShopType;
import com.example.infofusionback.service.ShopTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class InfoFusionBackApplication {

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

}
