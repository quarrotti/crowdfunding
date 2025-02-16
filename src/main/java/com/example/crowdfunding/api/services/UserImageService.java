package com.example.crowdfunding.api.services;

import com.example.crowdfunding.store.entities.about_user.UserImageEntity;
import com.example.crowdfunding.store.repositories.UserImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserImageService {
    private final UserImageRepository userImageRepository;
    private final UserService userService;

    public UserImageEntity findByUser(Principal principal){
        return userImageRepository.findByUser(userService.findByLogin(principal));
    }

    public UserImageEntity findByUserId(Long id){
        return userImageRepository.findByUser(userService.findById(id));
    }

    @Transactional
    public ResponseEntity<InputStreamResource> getUserPersonalAvatar(Principal principal) {
        byte[] avatarData = findByUser(principal).getData();

        if (avatarData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(avatarData));


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=avatar.jpg")
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(avatarData.length)
                .body(resource);
    }
    @Transactional
    public ResponseEntity<InputStreamResource> getUserAvatar(Long id) {
        byte[] avatarData = findByUserId(id).getData();

        if (avatarData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(avatarData));


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=avatar.jpg")
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(avatarData.length)
                .body(resource);
    }
}
