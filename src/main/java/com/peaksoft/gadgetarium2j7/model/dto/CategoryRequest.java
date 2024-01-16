package com.peaksoft.gadgetarium2j7.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CategoryRequest {
    private Set<String> names;
    private Set<String> subCategories;
}
