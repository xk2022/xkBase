package com.xk.car.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class VehicleRequest {

    @Schema(description ="車輛UUID" )
    private UUID uuid;

    @NotBlank(message = "車輛類型不能為空")
    @Schema(description = "車輛類型")
    private String vehicleType;

    @NotBlank(message = "車牌號碼不能為空")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "系統代碼只能包含大寫字母、數字或中線")
    @Size(min = 2, max = 50, message = "車牌號碼長度須介於 2 到 50 個字之間")
    @Schema(description = "車牌號碼",example = "AAA-1234")
    private String licensePlate;

    @Size(min = 2, max = 50, message = "品牌與型號長度須介於 2 到 50 個字之間")
    @Schema(description = "品牌與型號",example = "BENZ-XXX")
    private String brandModel;

    @Size(min = 2, max = 50, message = "出廠年份長度須介於 2 到 50 個字之間")
    @Schema(description = "出廠年份",example = "2025")
    private String year;


    @Schema(description = "車輛里程",example = "12000.5")
    private BigDecimal mileage;

    @NotBlank
    @Size(min = 2, max = 50, message = "車輛狀態長度須介於 2 到 50 個字之間")
    @Schema(description = "車輛狀態")
    private String status;



}
