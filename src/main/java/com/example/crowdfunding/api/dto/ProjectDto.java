package com.example.crowdfunding.api.dto;

import com.example.crowdfunding.store.entities.about_project.ProjectGenre;
import com.example.crowdfunding.store.entities.about_user.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDto {
    @NonNull
    String name;
    @NonNull
    String description;
    @NonNull
    Long required_amount;
    @NonNull
    Long collected_amount;
    @NonNull
    ProjectGenre genre;
    @NonNull
    UserEntity owner;
}
