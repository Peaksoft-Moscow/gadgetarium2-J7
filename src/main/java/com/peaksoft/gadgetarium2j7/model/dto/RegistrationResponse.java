package com.peaksoft.gadgetarium2j7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private String response;
}
