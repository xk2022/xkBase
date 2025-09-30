package com.xk.car.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 車輛里程耗損紀錄 請求參數
 * @author  hank created  2025/08/15
 */
@Data
public class VehicleTripLogsRequest {

    @Schema(description = "uuid")
    private String uuid;

    @Schema(description = "車牌號碼")
    private String licensePlate;

    @Schema(description = "對應派車單編號")
    private String orderId;

    @Schema(description = "出發里程")
    private String startMileage;

    @Schema(description = "返回里程")
    private String endMileage;

    @Schema(description = "行駛日期")
    private String date;

    @Schema(description = "創建人員")
    private String createdBy;

    @Schema(description = "更新人員")
    private String updatedBy;

}
