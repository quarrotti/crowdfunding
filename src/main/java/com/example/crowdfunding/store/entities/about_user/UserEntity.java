package com.example.crowdfunding.store.entities.about_user;

import com.example.crowdfunding.store.entities.about_project.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "app_user")
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email; // login

    String password; // password

    String nickname;
    
    String firstName;

    String lastName;

    Long bankAccountsNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    UserImageEntity image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    List<ProjectEntity> projects;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Если аккаунт не должен истекать
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Если аккаунт не должен быть заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Если учетные данные не должны истекать
    }

    @Override
    public boolean isEnabled() {
        return true; // Если аккаунт должен быть активен
    }
}
