package com.xk.common.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {

    private Long id;

    private String name;

    private boolean active;

    private List<PermissionDTO> permissionDTOs;

    private List<PermissionDTO.Action> actions;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Action{

        private Long id;

        private String name;

        private boolean active;

    }

}
