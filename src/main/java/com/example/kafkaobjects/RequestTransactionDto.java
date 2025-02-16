package com.example.kafkaobjects;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTransactionDto {
    @NonNull
    int amount;

    @NonNull
    Long sendersNumber;

    @NonNull
    Long recipientsNumber;

    @NonNull
    Long projectId;
}
