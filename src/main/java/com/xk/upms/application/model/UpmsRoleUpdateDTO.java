package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpmsRoleUpdateDTO(

		@Schema(description = "角色名稱")
		@NotBlank(message="請輸入角色名稱")
		String code,

		@Schema(description = "角色標題")
		@NotBlank(message="請輸入角色標題")
		String title,

		@Schema(description = "角色描述")
		String description,

		@Schema(description = "排序")
		@Min(value = 0, message = "排序不得小於0")
		@Max(value = 10, message = "排序不得大於100")
		@NotNull(message = "請輸入排序")
		Long orders
		
) {
}
