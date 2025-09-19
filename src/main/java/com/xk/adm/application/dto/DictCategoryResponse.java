package com.xk.adm.application.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class DictCategoryResponse {

    private String code;
    private String name;
    private String description;
    private List<DictItemResponse> itemList;
}
