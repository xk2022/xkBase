package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpmsUserRequestDTO(

        @Schema(description = "關鍵字")
        String keyword,

        @Schema(description = "是否啟用")
        Boolean enabled,

        @Schema(description = "是否鎖定")
        Boolean locked

) {
}
