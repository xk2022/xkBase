package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UpmsRoleCreateDTO (

	 @Schema(description = "角色名稱")
	 @NotBlank(message = "角色名稱不得為空")
	 String code,

	 @Schema(description = "角色標題")
	 @NotBlank(message = "角色標題不得為空")
	 String title,

	 @Schema(description = "角色描述")
	 String description,

	 @Schema(description = "系統uuid清單")
	 @NotNull(message = "系統uuid清單不得為空")
	 List<UUID> systemUuids,
	 
	 @Schema(description = "排序")
	 @Min(value = 0, message = "排序不得小於0")
	 @Max(value = 100, message = "排序不得大於100")
	 @NotNull(message = "排序不得為空")
	 Long orders
	 
){
}
