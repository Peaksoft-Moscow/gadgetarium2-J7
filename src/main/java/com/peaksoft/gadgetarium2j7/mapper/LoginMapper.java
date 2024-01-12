package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginResponse mapToResponse(String token, String roleNames) {
        return LoginResponse.builder()
                .roleName(roleNames)
                .token(token)
                .build();
    }


}
