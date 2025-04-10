package com.xk.common.util.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SystemDTO {

    private UUID systemUuid;

    private String name;

    private boolean enabled;

}
