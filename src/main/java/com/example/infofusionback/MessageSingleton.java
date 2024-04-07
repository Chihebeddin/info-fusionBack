package com.example.infofusionback;

public class MessageSingleton {

	protected static MessageSingleton INSTANCE;

	private MessageSingleton() {

	}

	public static MessageSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MessageSingleton();
		}
		return INSTANCE;
	}
	
	public String welcomeSubject() {
		return "Bienvenue chez ShopLoc !";
	}
	
	public String welcomeClient(String name) {
		return String.format("Cher(e) %s,\n "
				+ "Nous sommes ravis de vous accueillir chez ShopLoc, votre nouveau partenaire pour des achats locaux pratiques et avantageux !\r\n"
				+ "\r\n"
				+ "Grâce à ShopLoc, vous pouvez maintenant découvrir et soutenir les commerces locaux de votre quartier tout en bénéficiant de nombreux avantages :\r\n"
				+ "\r\n"
				+ "    Commandes en ligne simplifiées : Parcourez facilement les produits de vos commerces préférés et commandez en quelques clics depuis le confort de votre domicile.\r\n"
				+ "\r\n"
				+ "    Collecte en magasin (Click & Collect) : Choisissez l'option de collecte en magasin pour récupérer vos achats rapidement et facilement, tout en soutenant vos commerces locaux.\r\n"
				+ "\r\n"
				+ "    Carte de fidélité : Profitez d'une carte de fidélité intégrée qui vous récompense chaque fois que vous effectuez des achats chez nos commerces partenaires. Accumulez des points et débloquez des avantages exclusifs !\r\n"
				+ "\r\n"
				+ "    Itinéraire optimisé : Lorsque vous choisissez l'option Click & Collect, notre système vous indique le chemin le plus court pour récupérer vos achats, vous faisant gagner du temps et de l'énergie.\r\n"
				+ "\r\n"
				+ "    Offres spéciales : Bénéficiez d'offres spéciales et de réductions exclusives chez nos commerces partenaires, uniquement disponibles pour les membres de ShopLoc.\r\n"
				+ "\r\n"
				+ "Votre inscription est une étape importante pour soutenir votre communauté locale et profiter d'une expérience d'achat pratique et enrichissante. Nous sommes impatients de vous accompagner dans ce voyage !\r\n"
				+ "\r\n"
				+ "N'hésitez pas à explorer notre plateforme dès maintenant pour découvrir tout ce que ShopLoc a à vous offrir.\r\n"
				+ "\r\n"
				+ "Bienvenue à bord et bon shopping local !\r\n"
				+ "\r\n"
				+ "Cordialement,\r\n"
				+ "L'équipe ShopLoc", 
				name);
	}
	
	public String welcomeShop() {
		return String.format("Cher(e) commerçant(e),\r\n"
				+ "\r\n"
				+ "Nous sommes ravis de vous accueillir en tant que nouveau partenaire chez ShopLoc, la plateforme qui vous aide à promouvoir votre commerce local et à fidéliser vos clients !\r\n"
				+ "\r\n"
				+ "Grâce à ShopLoc, vous pouvez désormais bénéficier de nombreux avantages pour développer votre activité et renforcer votre présence dans la communauté locale :\r\n"
				+ "\r\n"
				+ "    Visibilité accrue : Votre commerce sera mis en avant sur notre plateforme, permettant aux clients locaux de découvrir vos produits et services.\r\n"
				+ "\r\n"
				+ "    Système de click & collect : Proposez à vos clients la possibilité de commander en ligne et de récupérer leurs achats en magasin, offrant ainsi une expérience d'achat pratique et sécurisée.\r\n"
				+ "\r\n"
				+ "    Carte de fidélité intégrée : Récompensez la fidélité de vos clients en leur offrant une carte de fidélité numérique, leur permettant de cumuler des points à chaque achat et de bénéficier d'avantages exclusifs.\r\n"
				+ "\r\n"
				+ "    Promotions ciblées : Profitez d'outils de marketing avancés pour cibler et fidéliser vos clients grâce à des offres spéciales et des promotions personnalisées.\r\n"
				+ "\r\n"
				+ "    Support dédié : Notre équipe est là pour vous accompagner à chaque étape, de la configuration de votre compte à l'optimisation de votre présence sur la plateforme.\r\n"
				+ "\r\n"
				+ "Votre inscription est une étape importante pour renforcer votre connexion avec la communauté locale et augmenter votre visibilité auprès des clients potentiels. Nous sommes impatients de travailler avec vous pour faire croître votre activité et réussir ensemble !\r\n"
				+ "\r\n"
				+ "N'hésitez pas à explorer notre plateforme dès maintenant pour découvrir toutes les fonctionnalités et les opportunités qui s'offrent à vous.\r\n"
				+ "\r\n"
				+ "Si vous avez des questions ou des besoins d'assistance, n'hésitez pas à nous contacter à %s. Notre équipe est là pour vous aider à réussir !\r\n"
				+ "\r\n"
				+ "Bienvenue chez ShopLoc et au plaisir de collaborer avec vous !\r\n"
				+ "\r\n"
				+ "Cordialement,\r\n"
				+ "L'équipe ShopLoc", 
				"mediss4u@gmail.com");
	}

}
