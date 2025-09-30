package com.xk.car.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 車輛狀態管理 請求參數
 * hank create on 2025/09/01
 */
@Data
public class VehicleStatusLogsRequest {

    @Schema(description = "uuid")
    private String uuid;

    @Schema(description = "車牌號碼")
    private String licensePlate;

    @Schema(description = "司機ID")
    private String driverId;

    @Schema(description = "車輛狀態")
    private String status;

    @Schema(description = "操作者 ID")
    private String operatorId;

    @Schema(description = "狀態說明")
    private String statusNote;

    @Schema(description = "創建人員")
    private String createdBy;

    @Schema(description = "更新人員")
    private String updatedBy;
}
