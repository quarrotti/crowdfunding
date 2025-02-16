package com.example.crowdfunding.store.repositories;

import com.example.crowdfunding.store.entities.about_project.ProjectImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImageEntity, Long> {
}
