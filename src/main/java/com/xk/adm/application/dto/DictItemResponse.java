package com.xk.adm.application.dto;

import lombok.Data;

@Data
public class DictItemResponse {

    private String catrgoryCode;
    private String itemCode;
    private String itemName;
    private String sortOrder;
    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
