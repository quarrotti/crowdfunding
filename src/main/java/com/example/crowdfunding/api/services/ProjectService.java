package com.example.crowdfunding.api.services;

import com.example.crowdfunding.api.dto.ProjectDto;
import com.example.crowdfunding.store.entities.about_project.ProjectEntity;
import com.example.crowdfunding.store.entities.about_project.ProjectGenre;
import com.example.crowdfunding.store.entities.about_project.ProjectImageEntity;
import com.example.crowdfunding.store.entities.about_user.UserEntity;
import com.example.crowdfunding.store.repositories.ProjectImageRepository;
import com.example.crowdfunding.store.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectImageRepository projectImageRepository;

    public void save(ProjectEntity project){
        projectRepository.save(project);
    }

    @Transactional
    public List<ProjectEntity> findByUser(Principal principal){
        return projectRepository.findAllByOwner(userService.findByLogin(principal));
    }
    @Transactional
    public List<ProjectEntity> findByUser(Long id){
        return projectRepository.findAllByOwner(userService.findById(id));
    }

    @Transactional
    public List<ProjectEntity> findAllByName(String name){
        return projectRepository.findAllByName(name);
    }

    @Transactional
    public List<ProjectEntity> findAllByGenre(String genre){
        return projectRepository.findAllByGenre(ProjectGenre.valueOf(genre));
    }

    @Transactional
    public ProjectEntity findById(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<ProjectEntity> findProjectsWithCollectedAmountAtLeast75Percent(){
        Pageable pageable = PageRequest.of(0, 10);
        return projectRepository.findProjectsWithCollectedAmountAtLeast75Percent(pageable);
    }
    @Transactional
    public List<ProjectEntity> findTop10NewProjects(){
        Pageable pageable = PageRequest.of(0, 10);
        return projectRepository.findTop10NewProjects(pageable);
    }
    @Transactional
    public void createProject(ProjectDto projectDto,
                              List<MultipartFile> images,
                              Principal principal) throws IOException {
        ProjectEntity project = new ProjectEntity();

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setRequired_amount(projectDto.getRequired_amount());
        project.setOwner(userService.findByLogin(principal));
        System.out.println(principal.getName());
        project.setGenre(projectDto.getGenre());


        projectRepository.save(project);

        for(MultipartFile image : images){
            if(!image.isEmpty()) {
                ProjectImageEntity projectImageEntity = new ProjectImageEntity();

                projectImageEntity.setFileName(image.getOriginalFilename());
                projectImageEntity.setFileType(image.getContentType());
                projectImageEntity.setData(image.getBytes());
                projectImageEntity.setProject(project);

                projectImageRepository.save(projectImageEntity);
            }
        }
    }

}
