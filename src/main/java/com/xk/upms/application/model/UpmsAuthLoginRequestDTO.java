package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpmsAuthLoginRequestDTO (

        @Schema(description="使用者名稱")
        @NotBlank(message = "用戶名稱不能為空")
        @Size(max = 50, message = "用戶名稱不能超過50個字符")
        String username,

        @Schema(description="使用者密碼")
        @NotBlank(message = "密碼不能為空")
        String password

) {
}
