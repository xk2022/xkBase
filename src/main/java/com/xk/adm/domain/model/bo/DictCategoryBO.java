package com.xk.adm.domain.model.bo;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class DictCategoryBO {

    private String uuid;
    private String code;
    private String name;
    private String description;
    private Boolean deleted;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;
    List<DictItemBO> itemList;
}
