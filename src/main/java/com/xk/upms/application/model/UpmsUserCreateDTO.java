package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record UpmsUserCreateDTO(

		@Schema(description = "帳號")
		@NotBlank(message = "請輸入帳號")
		String account,

		@Schema(description = "使用者名稱")
		@NotBlank(message = "請輸入使用者名稱")
		String username,

		@Schema(description = "信箱")
		@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
				message = "請輸入有效的 Email，例如：example@email.com")
		@NotBlank(message = "請輸入Email")
		String email,

		@Schema(description = "手機")
		@Pattern(regexp = "^09\\d{8}$", message = "請輸入有效的手機號碼，例如：0912345678")
		@NotBlank(message = "請輸入手機")
		String cellPhone,

		@Schema(description = "密碼")
		@NotBlank(message = "請輸入密碼")
		String password,

		@Schema(description = "角色uuid")
		@NotNull(message = "請選擇角色")
		UUID roleUuid

) {
}
