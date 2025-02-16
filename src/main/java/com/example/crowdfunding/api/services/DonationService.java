package com.example.crowdfunding.api.services;

import com.example.kafkaobjects.RequestTransactionDto;
import com.example.crowdfunding.store.entities.about_project.DonationEntity;
import com.example.crowdfunding.store.entities.about_project.ProjectEntity;
import com.example.crowdfunding.store.repositories.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final KafkaTemplate<String, RequestTransactionDto> kafkaTemplate;
    private final ProjectService projectService;
    private final DonationRepository donationRepository;

    public void getDonation(RequestTransactionDto requestTransactionDto){
        DonationEntity donation = new DonationEntity();

        donation.setSendersNumber(requestTransactionDto.getSendersNumber());
        donation.setProject(projectService.findById(requestTransactionDto.getProjectId()));
        donation.setAmount(requestTransactionDto.getAmount());

        ProjectEntity project = donation.getProject();
        if(project.getCollected_amount() != null) {
            project.setCollected_amount(project.getCollected_amount() + donation.getAmount());
        } else project.setCollected_amount((long) donation.getAmount());

        projectService.save(project);
        donationRepository.save(donation);
    }

    public void sendObject(RequestTransactionDto requestTransactionDto) {
        kafkaTemplate.send("crowdfunding-topic", requestTransactionDto);
    }
}
