package com.xk.exapmleFolder.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 📌 `UserRequestDTO` - 用於接收使用者創建/更新請求
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Schema(description = "用戶請求 DTO")
@Data
public class ExampleRequestDTO {

	@Schema(description = "用戶名稱", example = "john_doe")
	@NotBlank(message = "使用者名稱不能為空")
	private String username;

	@Schema(description = "電子郵件", example = "john@example.com")
	@Email(message = "請提供有效的電子郵件地址")
	private String email;

	@Size(min = 6, message = "密碼長度至少為 6 碼")
	@NotBlank(message = "密碼不可為空")
	private String password;

}
