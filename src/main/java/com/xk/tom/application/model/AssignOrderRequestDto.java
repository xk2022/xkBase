package com.xk.tom.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 AssignOrderRequestDto
 * - 用於 API 接收「指派訂單」的請求資料
 * <p>
 * 典型場景：
 * - 管理者手動派單
 * - 系統自動派單 (自動運算後仍會落到這個結構)
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
public class AssignOrderRequestDto {

    @Schema(description = "車輛 UUID", example = "3fda4a0b-7e7f-4f2e-a18c-05dd7c3d2e1c")
    @NotNull(message = "車輛 ID 不可為空")
    private UUID vehicleId;

    @Schema(description = "司機 UUID", example = "c8b177f2-6d5f-4a8c-a181-4e6a3a40a6ef")
    @NotNull(message = "司機 ID 不可為空")
    private UUID driverId;

    @Schema(description = "指派人 UUID (管理者)", example = "f91d8e9a-4d1a-463d-99c1-182e2f6c5e7a")
    @NotNull(message = "指派人不可為空")
    private UUID assignedBy;

    @Schema(description = "指派時間", example = "2025-08-06T10:30:00+08:00[Asia/Taipei]")
    private ZonedDateTime assignedAt = ZonedDateTime.now();

}
