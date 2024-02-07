package com.peaksoft.gadgetarium2j7.controller;
import com.peaksoft.gadgetarium2j7.model.dto.RegistrationRequest;
import com.peaksoft.gadgetarium2j7.model.dto.RegistrationResponse;
import com.peaksoft.gadgetarium2j7.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@Tag(name = "This is UserController")
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User")
@SecurityRequirement(name = "Authorization")
public class UserController {

    private final UserService userService;

    @Operation(description = "This method registered new User")
    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = userService.registration(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
