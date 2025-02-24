package com.xk.upms.application.model;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.Comment;

import com.xk.upms.domain.model.po.UpmsPermission;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpmsPermissionUpdateDTO (
		@Schema(description = "所屬系統")
		   Long systemId,
		   
		   @Schema(description = "所屬上層")
		   Long pid,
		
		   @Schema(description = "權限名稱")
		   @NotBlank(message="權限名稱不得為空")
		   String name,
		   
		   @Schema(description = "權限路徑")
		   @NotBlank(message="權限路徑不得為空")
		   String uri,
		   
		   @Schema(description = "權限狀態")
		   Boolean status,
		   
		   @Schema(description = "排序")
		   @Min(value = 0, message = "排序不得小於0")
		   @Max(value = 100, message = "排序不得大於100")
		   @NotNull(message = "排序不得為空")
		   Long orders,
		   
		   @Comment("是否刪除狀態（0:刪除, 1:未刪除）")
		   Boolean isDeleted,
		   
		   @Comment("刪除的使用者名稱")
		   String deleteUser,
		   
		   @Comment("用戶被刪除的時間")
		   ZonedDateTime deleteTime,
		   
		   @Comment("子權限")
		   List<UpmsPermission> children,
		   
		   @Comment("父權限")
		   UpmsPermission parent
		   
){
}
