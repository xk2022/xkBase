package com.xk.exapmleFolder.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Sample request")
public class UserCreateRequest {

    @Schema(description = "案件GUID")
    @NotEmpty
    private String name;
    private String email;
    private String password;
}