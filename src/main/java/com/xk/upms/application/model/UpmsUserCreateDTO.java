package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpmsUserCreateDTO(

		@Schema(description = "使用者名稱")
		@NotBlank(message = "使用者名稱不得為空")
		String username,

		@Schema(description = "信箱")
		@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
		@NotBlank(message = "信箱不得為空")
		String email,

		@Schema(description = "手機")
		@NotBlank(message = "手機不得為空")
		String cellPhone,

		@Schema(description = "密碼")
		@NotBlank(message = "密碼不得為空")
		String password,
		
		@Schema(description="狀態")
		String locked

) {
}
