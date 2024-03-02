package com.example.infofusionback.service;
import com.example.infofusionback.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockNotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProductService productService;

    public void checkStockAndSendNotifications() {
        List<Product> products = productService.allProducts();
        for (Product product : products) {
            if (product.getQuantity() < product.getSafetyStock()) {
                // Envoyer une notification par e-mail pour le stock bas
                sendNotificationByEmail(product);
            }
        }
    }

    private void sendNotificationByEmail(Product product) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("destinataire@example.com");
        mailMessage.setSubject("Notification de stock bas pour le produit " + product.getName());
        mailMessage.setText("Le stock du produit " + product.getName() + " est bas. Stock actuel : " + product.getQuantity());

        javaMailSender.send(mailMessage);
    }
}

