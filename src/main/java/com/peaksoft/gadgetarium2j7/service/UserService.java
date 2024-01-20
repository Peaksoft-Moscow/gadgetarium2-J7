package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.UserMapper;
import com.peaksoft.gadgetarium2j7.model.dto.RegistrationRequest;
import com.peaksoft.gadgetarium2j7.model.dto.RegistrationResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Role;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public RegistrationResponse registration(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
        System.out.println(request.getPassword());
        System.out.println(user.getPassword());
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("someone has the same email, please change your email");
            throw new RuntimeException("Email is not unique!");
        }
        if (!user.getEmail().contains("@")) {
            log.error("this isn't email, because your email doesn't have symbol '@'");
            throw new RuntimeException("Email doesn't have symbol '@'");
        }

        if (user.getPassword().length() < 6) {
            log.error("the password is short, make at least 6 letters");
            throw new RuntimeException("Password is short");
        }

        if (!user.getPassword().contains("0") &&
                !user.getPassword().contains("1") &&
                !user.getPassword().contains("2") &&
                !user.getPassword().contains("3") &&
                !user.getPassword().contains("4") &&
                !user.getPassword().contains("5") &&
                !user.getPassword().contains("6") &&
                !user.getPassword().contains("7") &&
                !user.getPassword().contains("8") &&
                !user.getPassword().contains("9")) {
            log.error("your password must have at least 1 digit");
            throw new RuntimeException("password without digit");
        }

        boolean pass = false;
        for (char ch = 65; ch < 91; ch++) {
            if (user.getPassword().contains("" + ch)) {
                pass = true;
                break;
            }
        }
        if (!pass) {
            for (char ch = 97; ch < 123; ch++) {
                if (user.getPassword().contains("" + ch)) {
                    pass = true;
                    break;
                }
            }
            if (!pass) {
                log.error("your password must have at least 1 letter");
                throw new RuntimeException("password without letter");
            }
        }

        List<Role> roles = new ArrayList<>();
        Role role = new Role("USER");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.registration(user);
    }
}
