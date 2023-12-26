package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.RegistrationRequest;
import com.peaksoft.gadgetarium2j7.model.dto.RegistrationResponse;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserMapper {

        public User mapToEntity(RegistrationRequest request){
                User user = new User();
                user.setFirstName(request.getFirstName());
                user.setLastName(request.getLastName());
                user.setTelNumber(request.getTelNumber());
                user.setEmail(request.getEmail());
                user.setPassword(request.getPassword());
                user.setCreateDate(LocalDate.now());
                return user;
        }

        public RegistrationResponse registration(User user){
                return RegistrationResponse.
                        builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .telNumber(user.getTelNumber())
                        .email(user.getEmail())
                        .response("Success Registration")
                        .build();
        }
}
