package com.example.crowdfunding.api.contollers;

import com.example.crowdfunding.api.services.ProjectImageService;
import com.example.crowdfunding.api.services.ProjectService;
import com.example.crowdfunding.api.services.UserImageService;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ImageController {
    private final UserImageService userImageService;
    private final ProjectService projectService;
    private final ProjectImageService projectImageService;

    @GetMapping("/personal-avatar")
    public ResponseEntity<InputStreamResource> getUserPersonalAvatar(Principal principal) {
        return userImageService.getUserPersonalAvatar(principal);
    }

    @GetMapping("/avatar/{id}")
    public ResponseEntity<InputStreamResource> getUserAvatar(@PathVariable Long id) {
        return userImageService.getUserAvatar(id);
    }

    @GetMapping("/images/{id}")
    private ResponseEntity<InputStreamResource> getProjectImageById(@PathVariable Long id) {
        return projectImageService.getProjectImageById(id);
    }
}
