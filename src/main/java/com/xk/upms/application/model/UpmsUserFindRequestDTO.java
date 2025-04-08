package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpmsUserFindRequestDTO(

        @Schema(description = "關鍵字")
        String keyword,

        @Schema(description = "是否啟用")
        Boolean enabled,

        @Schema(description = "是否鎖定")
        Boolean locked

) {
}
