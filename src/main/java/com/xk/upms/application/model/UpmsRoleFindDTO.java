package com.xk.upms.application.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpmsRoleFindDTO(

        @Schema(description = "關鍵字")
        String keyword

) {
}
