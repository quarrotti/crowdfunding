package com.example.crowdfunding.api.services;

import com.example.kafkaobjects.RequestTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, RequestTransactionDto> template;
    private final DonationService donationService;

    public void sendRequest(RequestTransactionDto requestTransactionDto){
        template.send("request-topic",null, requestTransactionDto);
    }

    @KafkaListener(topics = "response-topic", groupId = "response-banking")
    public void getResponse(RequestTransactionDto requestTransactionDto){
        donationService.getDonation(requestTransactionDto);
    }
}
