package com.example.crowdfunding.api.services;

import com.example.crowdfunding.store.entities.about_user.UserEntity;
import com.example.crowdfunding.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<UserEntity> findByLogin(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException(String
                        .format("Пользователь с таким email не найдет")));
        return user;
    }
}
