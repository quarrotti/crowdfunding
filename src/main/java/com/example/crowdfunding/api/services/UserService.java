package com.example.crowdfunding.api.services;

import com.example.crowdfunding.api.dto.UserDto;
import com.example.crowdfunding.store.entities.about_user.UserEntity;
import com.example.crowdfunding.store.repositories.UserImageRepository;
import com.example.crowdfunding.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.crowdfunding.store.entities.about_user.UserImageEntity;

import java.io.IOException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity findByLogin(Principal principal){
        return userRepository.findByEmail(principal.getName()).orElse(null);
    }
    @Transactional
    public UserEntity findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public void createUser(UserDto userDto, MultipartFile imageFile) throws IOException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email уже существует");
        }

        UserEntity userForSave = new UserEntity();
        userForSave.setEmail(userDto.getEmail());
        userForSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userForSave.setNickname(userDto.getNickname());
        userForSave.setFirstName(userDto.getFirstName());
        userForSave.setLastName(userDto.getLastName());
        userForSave.setBankAccountsNumber(userDto.getBankAccountsNumber());

        userRepository.save(userForSave);

        UserImageEntity imageEntity = new UserImageEntity();
        imageEntity.setFileName(imageFile.getOriginalFilename());
        imageEntity.setFileType(imageFile.getContentType());
        imageEntity.setData(imageFile.getBytes());
        imageEntity.setUser(userForSave); // Связываем изображение с пользователем

        userImageRepository.save(imageEntity);
    }
}
