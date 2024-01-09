package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public LoginResponse mapToResponse(String token, String roleName) {
        return LoginResponse.builder()
                .token(token)
                .roleName(roleName)
                .build();
    }
}
