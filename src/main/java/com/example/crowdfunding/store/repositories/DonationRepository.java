package com.example.crowdfunding.store.repositories;

import com.example.crowdfunding.store.entities.about_project.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {
}
