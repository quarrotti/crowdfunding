package com.example.crowdfunding.api.services;

import com.example.crowdfunding.store.entities.about_project.ProjectImageEntity;
import com.example.crowdfunding.store.repositories.ProjectImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class ProjectImageService {
    private final ProjectImageRepository projectImageRepository;

    public ProjectImageEntity findById(Long id){
        return projectImageRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<InputStreamResource> getProjectImageById(Long id) {
        byte[] imageData = findById(id).getData();

        if (imageData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(imageData));


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=projectImage.jpg")
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(imageData.length)
                .body(resource);
    }
}
