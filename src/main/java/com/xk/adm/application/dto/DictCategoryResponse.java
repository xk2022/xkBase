package com.xk.adm.application.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DictCategoryResponse {

    private String code;
    private String name;
    private String description;
    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
