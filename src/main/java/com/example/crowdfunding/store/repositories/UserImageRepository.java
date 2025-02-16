package com.example.crowdfunding.store.repositories;

import com.example.crowdfunding.store.entities.about_user.UserEntity;
import com.example.crowdfunding.store.entities.about_user.UserImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {
    UserImageEntity findByUser(UserEntity user);
}
