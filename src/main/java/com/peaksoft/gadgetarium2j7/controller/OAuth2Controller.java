package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.service.OAuth2Service;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
@Tag(name = "OAuth")
@SecurityRequirement(name = "Authorization")
public class OAuth2Controller {
    private final OAuth2Service oAuth2Service;

    @GetMapping("/")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }

    @GetMapping("/with-google")
    public Map<String, Object> addUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException {
        return oAuth2Service.saveWithGoogle(oAuth2AuthenticationToken);
    }
}
