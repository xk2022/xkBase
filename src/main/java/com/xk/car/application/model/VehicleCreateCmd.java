package com.xk.car.application.model;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class VehicleCreateCmd {

    @Schema(description = "車輛ID")
    private UUID uuid;

    @Schema(description = "車頭/板車")
    private VehicleEnum vehicleType;

    @Schema(description = "車牌號碼")
    private String licensePlate;

    @Schema(description = "品牌與型號")
    private String brandModel;

    @Schema(description = "出廠年份")
    private String year;

    @Schema(description = "初始里程數")
    private BigDecimal mileage;

    @Schema(description = "車輛狀態")
    private VehicleStatusEnum status;

    @Schema(description = "創建時間")
    private ZonedDateTime createdAt;

    @Schema(description = "編輯時間")
    private ZonedDateTime updatedAt;


}
