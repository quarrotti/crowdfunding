package com.example.crowdfunding.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @NonNull
    String email; // login
    @NonNull
    String password; // password
    @NonNull
    String nickname;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    Long bankAccountsNumber;

}
