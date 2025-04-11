package com.xk.upms.application.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.UUID;

public record UpmsRolePermissionRequestDTO(

        @Schema(description="角色ID")
        @NotBlank(message = "角色id 不能為空")
        Long roleId,

        @Schema(description="權限ID")
        @NotBlank(message = "權限ID 不能為空")
        Long permissionId,


        @Schema(description="系統uuid")
        @NotBlank(message = "系統uuid 不能為空")
        UUID systemUuid,

        @Schema(description="啟用狀態")
        Boolean active,

        @Schema(description="鎖定狀態（0:刪除, 1:未刪除）")
        Boolean isDeleted,


        @Schema(description="刪除的使用者名稱")
        String deleteUser,

        @Schema(description="用戶被刪除的時間")
        ZonedDateTime deleteTime
) {
}
