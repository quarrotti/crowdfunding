package com.example.crowdfunding.api.contollers;


import com.example.crowdfunding.api.services.ProjectService;
import com.example.crowdfunding.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping("/personal-profile")
    public String personalProfile(Model model, Principal principal){
        model.addAttribute("user", userService.findByLogin(principal));
        model.addAttribute("projects", projectService.findByUser(principal));

        return "user-pages/personal-profile";
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("projects", projectService.findByUser(id));
        return "user-pages/profile";
    }
}
