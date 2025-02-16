package com.example.crowdfunding.store.repositories;

import com.example.crowdfunding.store.entities.about_project.ProjectEntity;
import com.example.crowdfunding.store.entities.about_project.ProjectGenre;
import com.example.crowdfunding.store.entities.about_user.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query("SELECT p FROM ProjectEntity p WHERE p.collected_amount >= 0.75 * p.required_amount")
    List<ProjectEntity> findProjectsWithCollectedAmountAtLeast75Percent(Pageable pageable);

    @Query("SELECT p FROM ProjectEntity p ORDER BY p.createdAt DESC")
    List<ProjectEntity> findTop10NewProjects(Pageable pageable);

    List<ProjectEntity> findAllByOwner(UserEntity user);

    List<ProjectEntity> findAllByName(String name);

    List<ProjectEntity> findAllByGenre(ProjectGenre genre);

}
