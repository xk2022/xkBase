package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 📌 OrderUpdateStatusCmd
 * - 更新訂單狀態用的 Command
 * - 用於 Service 執行狀態轉換邏輯
 * <p>
 * 👉 對應的 API: POST /api/orders/{uuid}/status
 * 👉 流程：
 * Controller → UseCase (UpdateOrderStatusRequestDto → OrderUpdateStatusCmd) → Service.updateStatus()
 * <p>
 * ⚠️ 須檢查狀態轉換是否合法 (在 Entity / Domain Service)
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class OrderUpdateStatusCmd {

    @Schema(description = "目標狀態", example = "IN_TRANSIT")
    private OrderStatus newStatus;

    @Schema(description = "操作者 ID (可能是司機 or 管理員)", example = "9c1a2b3d-4e5f-6789-abcd-ef0123456789")
    private UUID operatorId;

    @Schema(description = "狀態更新時間", example = "2025-08-06T15:45:00+08:00[Asia/Taipei]")
    private LocalDate timestamp;

}
