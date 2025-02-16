package com.example.crowdfunding.store.entities.about_project;

import com.example.crowdfunding.store.entities.about_user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "project")
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    Long required_amount;

    LocalDateTime createdAt;

    Long collected_amount;


    @Enumerated(EnumType.STRING)
    ProjectGenre genre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
    List<ProjectImageEntity> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
    List<DonationEntity> donations;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    UserEntity owner;

    @PrePersist
    public void init(){
        createdAt = LocalDateTime.now();
        collected_amount = null;
    }
}
