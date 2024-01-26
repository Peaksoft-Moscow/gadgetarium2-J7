package com.peaksoft.gadgetarium2j7.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class CommentRequest {
    @Min(value=1)
    @Max(value=5)
    private int rating;
    private String comment;
    private MultipartFile img;
}
