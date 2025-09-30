package com.xk.adm.domain.model.bo;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DictItemBO {

    private String itemUuid;
    private String categoryCode;
    private String itemCode;
    private String itemName;
    private Integer sortOrder;
    private Boolean deleted;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;
}
