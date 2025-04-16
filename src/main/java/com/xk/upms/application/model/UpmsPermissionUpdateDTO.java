package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UpmsPermissionUpdateDTO (

		@Schema(description = "權限清單")
		@NotNull(message = "權限清單不得為空")
		List<Permission> permissions

){

		public record Permission(

				@Schema(description = "權限uuid")
				@NotNull(message = "權限uuid不得為空")
				UUID uuid,

				@Schema(description = "是否啟用")
				@NotNull(message = "是否啟用不得為空")
				boolean active,

				@Schema(description = "子權限清單")
				List<UpmsPermissionUpdateDTO.Permission> permissions,

				@Schema(description = "動作清單")
				List<UpmsPermissionUpdateDTO.Action> actions

		){}

		public record Action(

				@Schema(description = "動作uuid")
				@NotNull(message = "動作uuid不得為空")
				UUID uuid,

				@Schema(description = "是否啟用")
				@NotNull(message = "是否啟用不得為空")
				boolean active

		){}

}
