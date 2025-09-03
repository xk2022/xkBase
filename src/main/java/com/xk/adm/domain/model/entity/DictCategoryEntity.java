package com.xk.adm.domain.model.entity;


import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class DictCategoryEntity {

    private UUID uuid;
    private String code;
    private String name;
    private String description;
    private Boolean deleted;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;

    public void initialize(){
        this.createdTime = ZonedDateTime.now();
    }
}
