package com.xk.adm.application.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class DictItemListResponse {

    private String cateGoryCode;
    private String name;
    private String description;
    private List<DictItemResponse> dictItemResponseList;
    private String createdTime;


}
