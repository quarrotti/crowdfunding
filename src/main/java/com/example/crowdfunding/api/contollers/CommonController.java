package com.example.crowdfunding.api.contollers;

import com.example.crowdfunding.api.dto.UserDto;
import com.example.crowdfunding.api.services.ProjectService;
import com.example.crowdfunding.api.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final UserService userService;
    private final ProjectService projectService;
    @GetMapping("/")
    public String welcomePage(){
        return "common-pages/welcome-page";
    }

    @GetMapping("/general-page")
    public String generalPage(Model model){
        model.addAttribute("new_projects", projectService.findTop10NewProjects());
        model.addAttribute("nc_projects", projectService.findProjectsWithCollectedAmountAtLeast75Percent());
        return "common-pages/general-page";
    }

    @GetMapping("/register")
    public String registerG(){
        return "common-pages/register&login/register";
    }

    @PostMapping("/register")
    public String registerP(UserDto userDto, MultipartFile imageFile){
        try {
            userService.createUser(userDto, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/general-page";
    }

    @GetMapping("/login")
    public String login(){
        return "common-pages/register&login/login";
    }

    @GetMapping("/failed-authorization")
    public String fail(){
        return "common-pages/register&login/fail";
    }
}
