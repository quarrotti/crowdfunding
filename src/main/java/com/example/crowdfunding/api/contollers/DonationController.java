package com.example.crowdfunding.api.contollers;

import com.example.kafkaobjects.RequestTransactionDto;
import com.example.crowdfunding.api.services.KafkaService;
import com.example.crowdfunding.api.services.ProjectService;
import com.example.crowdfunding.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class DonationController {
    private final UserService userService;
    private final ProjectService projectService;
    private final KafkaService kafkaService;

    @GetMapping("/donation/{id}")
    public String donationG(@PathVariable Long id, Model model){
        model.addAttribute("project", projectService.findById(id));
        return "donation-pages/donation-page";
    }

    @PostMapping("/donation/{id}")
    public String donationP(@PathVariable Long id,
                           int amount,
                           Principal principal){
        RequestTransactionDto requestTransactionDto = new RequestTransactionDto();
        requestTransactionDto.setSendersNumber(userService.findByLogin(principal).getBankAccountsNumber());
        requestTransactionDto.setRecipientsNumber(projectService.findById(id).getOwner().getBankAccountsNumber());
        requestTransactionDto.setAmount(amount);
        requestTransactionDto.setProjectId(id);

        kafkaService.sendRequest(requestTransactionDto);

        return "donation-pages/after-donation";
    }
}
