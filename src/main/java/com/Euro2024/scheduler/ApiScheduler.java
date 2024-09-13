package com.Euro2024.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiScheduler {
    private final RestTemplate restTemplate = new RestTemplate();

    // Este método se ejecutará cada 15 minutos
    @Scheduled(fixedRate = 300000)
    public void callApi() {
        try{

            String url = "https://euro2024-naqw.onrender.com/v1/player?pageNo=1&pageSize=10";
            restTemplate.getForObject(url, String.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
