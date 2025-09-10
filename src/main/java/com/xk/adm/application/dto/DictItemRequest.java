package com.xk.adm.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DictItemRequest {

    private String uuid;

    @NotBlank
    private String catrgoryCode;

    @NotBlank
    private String itemCode;

    @NotBlank
    private String itemName;

    private Integer sortOrder;

}
