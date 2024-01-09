package com.peaksoft.gadgetarium2j7.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {

    Long id;
    @NotNull(message = "username shouldn't be null")
    String name;
    @Email(message = "invalid email address")
    String email;
    @NotNull(message = "user lastName shouldn't be null")
    String lastName;
    String roleName;
    LocalDateTime created;

}
