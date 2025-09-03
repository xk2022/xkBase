package com.xk.adm.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DictCategoryRequest {

    private String uuid;

    @NotBlank
    private String code;
    @NotBlank
    private String name;

    private String description;
}
