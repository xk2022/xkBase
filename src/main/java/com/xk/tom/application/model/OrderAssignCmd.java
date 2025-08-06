package com.xk.tom.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 OrderAssignCmd
 * - 指派訂單用的 Command
 * - 用於 Service 執行業務邏輯（指派車輛 & 司機）
 * <p>
 * 👉 對應的 API: POST /api/orders/{uuid}/assign
 * 👉 流程：
 * Controller → UseCase (AssignOrderRequestDto → OrderAssignCmd) → Service.assign()
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
public class OrderAssignCmd {

    @Schema(description = "指派車輛 ID", example = "a3f5c4d2-91b7-42de-b891-7d9f1c5a1d23")
    private UUID vehicleId;

    @Schema(description = "指派司機 ID", example = "d8c6e1f4-8f9a-45ab-bb53-5a92f3e12e6b")
    private UUID driverId;

    @Schema(description = "操作人員 ID (管理員)", example = "b1a3e4c5-7f2a-43d9-9f5e-8c1d2e4b7a6f")
    private UUID assignedBy;

    @Schema(description = "指派時間", example = "2025-08-06T14:30:00+08:00[Asia/Taipei]")
    private ZonedDateTime assignedAt;

}
