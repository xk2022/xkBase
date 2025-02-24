package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Comment;

import java.util.List;

public record UpmsPermissionCreateDTO (
		
		@Schema(description = "系統id")
		Long systemId,

		@Schema(description = "角色id")
		Long roleId,

		@Schema(description = "權限清單")
		List<Permission> permissions


) {

		public record Permission(

				@Schema(description = "所屬上層id")
				@NotNull(message = "權限id不得為空")
				Long pid,

				@Schema(description = "權限名稱")
				@NotBlank(message = "權限名稱不得為空")
				String name,

				@Schema(description = "權限路徑")
				@NotBlank(message = "權限路徑不得為空")
				String uri,

				@Schema(description = "權限狀態")
				Boolean status,

				@Schema(description = "排序")
				@Min(value = 0, message = "排序不得小於0")
				@Max(value = 100, message = "排序不得大於100")
				@NotNull(message = "排序不得為空")
				Long orders,

				@Comment("子權限")
				List<Permission> childrens

		){
		}

}
