package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.config.jwt.JwtUtil;
import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.mapper.LoginMapper;
import com.peaksoft.gadgetarium2j7.model.dto.LoginRequest;
import com.peaksoft.gadgetarium2j7.model.dto.LoginResponse;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SecurityRequirement(name = "Authorization")
public class AuthController {

    UserRepository userRepository;
    LoginMapper loginMapper;
    JwtUtil jwtUtil;
    UserDetailsService userDetailsService;

    @PostMapping("sign-in")
    @Operation(description = "Аутентификация пользователя и возвращение токенов и ролей")
    public LoginResponse signIn(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с email: " + loginRequest.getEmail()));
        String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(loginRequest.getEmail()));
        return loginMapper.mapToResponse(token, user.getRoles().get(0).getName());
    }
}




