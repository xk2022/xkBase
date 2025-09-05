package com.xk.car.application.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VehiclePartsUsageRequest {

    @Schema(description ="UUID" )
    private String uuid;

    @Schema(description = "車牌號碼")
    private String licensePlate;


    @Schema(description = "耗損或更換零件名稱")
    private String partName;

    @Schema(description = "當時里程")
    private String mileage;

    @Schema(description = "更換或維修成本")
    private String cost;

    @Schema(description = "備註或說明")
    private String description;

    @Schema(description = "使用或更換日期")
    private String usedAt;



}
