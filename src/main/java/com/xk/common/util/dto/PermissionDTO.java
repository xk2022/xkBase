package com.xk.common.util.dto;

import lombok.Data;

import java.util.List;

@Data
public class PermissionDTO {

    private Long id;

    private String name;

    private boolean active;

    private List<PermissionDTO> permissionDTOs;

    private List<PermissionDTO.Action> actions;

    @Data
    public static class Action{

        private Long id;

        private String name;

        private boolean active;

    }

}
