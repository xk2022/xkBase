package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UpmsRoleCreateDTO(

		@Schema(description = "角色名稱") @NotBlank(message = "角色名稱不得為空") String code,

		@Schema(description = "角色標題") @NotBlank(message = "角色標題不得為空") String title,

		@Schema(description = "角色描述") String description,

		@Schema(description = "排序") String orders

) {
}
