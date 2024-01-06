package com.peaksoft.gadgetarium2j7.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {

    @NotNull(message = "username shouldn't be null")
    String name;
    @Email(message = "invalid email address")
    String email;
    @NotNull(message = "user lastName shouldn't be null")
    String lastName;
    @NotNull
    @Size(min = 4, max = 12, message = "password should have at least 12 characters")
    String password;
    @NotNull
    @Size(min = 4, max = 12, message = "password should have at least 12 characters")
    String repeatPassword;

}
