package com.br.desafio.service;

import com.telesign.MessagingClient;
import com.telesign.RestClient;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

        public boolean sendSMS(String position) {

            String customerId = "CACE77CC-8F32-4A8E-A192-97BFB536CE63";
            String apiKey = "tG6d2jGj+y8/0uc8vL7KsaYAZuSZ6DSp7MeteQp8ZXe5APY0NoiIzC/7LQXzsOGHlFZ7SkIs8NIQ1L8GUgNvEg==";
            String phoneNumber = "+5581998097240";
            String message = "Posição na fila:" + position;
            String messageType = "ARN";

            try {
                MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
                RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
                System.out.println("Your reference id is: "+telesignResponse.json.get("reference_id"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}
