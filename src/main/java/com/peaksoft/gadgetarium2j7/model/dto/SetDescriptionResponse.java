package com.peaksoft.gadgetarium2j7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SetDescriptionResponse {
    private String video;
    private String pdf;
    private String description;
}
