package com.example.infofusionback.entity;
import com.example.infofusionback.service.StockNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockCheckScheduler {

    @Autowired
    private StockNotificationService stockNotificationService;

    @Scheduled(fixedRate = 60000) // Vérification du stock toutes les 60 secondes
    public void checkStock() {
        stockNotificationService.checkStockAndSendNotifications();
    }
}
