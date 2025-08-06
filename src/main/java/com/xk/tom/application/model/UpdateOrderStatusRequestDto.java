package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 UpdateOrderStatusRequestDto
 * - 用於 API 更新訂單狀態的 Request DTO
 * - Controller 接收後傳遞至 UseCase
 * <p>
 * 👉 典型流程：
 * Controller → UseCase.updateStatus(uuid, request)
 * → Mapper.toUpdateStatusCmd(request)
 * → Service.updateStatus(uuid, cmd)
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class UpdateOrderStatusRequestDto {

    @NotNull
    @Schema(description = "新狀態", example = "IN_TRANSIT")
    private OrderStatus status;

    @NotNull
    @Schema(description = "操作者 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID operatorId;

    @Schema(description = "更新時間戳記 (若未提供，系統自動填入 Now)",
            example = "2025-08-06T14:30:00+08:00[Asia/Taipei]")
    private ZonedDateTime timestamp;

}
