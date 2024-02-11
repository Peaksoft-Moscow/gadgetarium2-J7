package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2Service {
    private final UserRepository userRepository;

    public Map<String, Object> saveWithGoogle(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException {
        OAuth2AuthenticatedPrincipal principal = oAuth2AuthenticationToken.getPrincipal();
        if (oAuth2AuthenticationToken == null) {
            throw new IllegalAccessException("The token must not be null");
        }
        Map<String, Object> attributes = principal.getAttributes();
        User user = new User();
        user.setName((String) attributes.get("given_name"));
        user.setLastName((String) attributes.get("family_name"));
        user.setEmail((String) attributes.get("email"));
        user.setPassword((String) attributes.get("given_name"));
        user.setCreateDate(LocalDate.now());
        userRepository.save(user);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("name", user.getName());
        response.put("lastName", user.getLastName());
        response.put("email", user.getEmail());
        response.put("createDate", user.getCreateDate());
        return response;
    }
}
