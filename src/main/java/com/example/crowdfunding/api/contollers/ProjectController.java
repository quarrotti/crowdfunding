package com.example.crowdfunding.api.contollers;

import com.example.crowdfunding.api.dto.ProjectDto;
import com.example.crowdfunding.api.services.ProjectService;
import com.example.crowdfunding.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    @GetMapping("/create-project")
    public String createProjectG(){
        return "project-pages/create-project";
    }

    @PostMapping("/create-project")
    public String createProjectP(ProjectDto projectDto,
                                 List<MultipartFile> images,
                                 Principal principal){
        try {
            projectService.createProject(projectDto, images, principal);
            return "redirect:/general-page";
        } catch (IOException e) {
            return "common-pages/register&login/fail"; //todo
        }
    }

    @GetMapping("/project/{id}")
    public String project(@PathVariable Long id, Model model, Principal principal){
        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("images", projectService.findById(id).getImages());
        model.addAttribute("currentUser", userService.findByLogin(principal));
        return "project-pages/project-page";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String genre,
                         Model model) {
        if (StringUtils.hasText(name)) {
            model.addAttribute("projects", projectService.findAllByName(name));
        } else if (StringUtils.hasText(genre)) {
            model.addAttribute("projects", projectService.findAllByGenre(genre));
        }
        return "project-pages/current-projects";
    }


}
