package com.xk.adm.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 📌 `AdmSystemCreateDTO` - 系統資訊 DTO
 * 
 * - **確保 `code`、`name` 非空，並符合格式**
 * - **限制 `description` 長度**
 * - **支援 Swagger API 文檔**
 * - **提供清晰的數據驗證**
 * 
 * @author yuan Created on 2025/02/26.
 */
@Data
public class AdmSystemCreateDTO {

    @NotBlank(message = "系統代碼不能為空")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "系統代碼只能包含大寫字母、數字或底線")
    @Size(min = 2, max = 20, message = "系統代碼長度須介於 2 到 20 個字之間")
    @Schema(description = "系統代碼，必須為大寫字母或數字", example = "UPMS")
    private String code;
    
    @NotBlank(message = "系統名稱不能為空")
    @Size(min = 2, max = 50, message = "系統名稱長度須介於 2 到 50 個字之間")
    @Schema(description = "系統名稱，簡要描述系統用途", example = "用戶權限管理系統")
    private String name;
    
    @Size(max = 255, message = "描述不能超過 255 個字")
    @Schema(description = "系統描述，詳細介紹此系統的功能", example = "此系統負責管理用戶權限、角色與資源存取")
    private String description;
}
