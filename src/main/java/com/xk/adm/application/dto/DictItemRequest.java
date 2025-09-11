package com.xk.adm.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DictItemRequest {

    private String uuid;

    @NotBlank(message = "類別代碼不可空")
    private String catrgoryCode;

    @NotBlank(message = "選單項目")
    private String itemCode;

    @NotBlank
    private String itemName;

    private Integer sortOrder;

}
