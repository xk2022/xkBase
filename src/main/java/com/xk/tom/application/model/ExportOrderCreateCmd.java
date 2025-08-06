package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.model.enums.OrderType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * 📌 ExportOrderCreateCmd
 * - 建立出口訂單用的 Command
 * - 從 {@link ExportOrderRequestDto} 轉換而來
 * - 提供給 Domain Service 使用
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
public class ExportOrderCreateCmd {

    @Schema(description = "客戶 ID", example = "10001")
    @NotNull(message = "客戶 ID 不可為空")
    private Long customerId;

    @Schema(description = "出口日期", example = "2025-08-10")
    @NotNull(message = "出口日期不可為空")
    private LocalDate shipDate;

    @Schema(description = "船公司", example = "EVERGREEN")
    private String shippingCompany;

    @Schema(description = "船名/航次", example = "YM WARMTH / 123E")
    private String vesselVoyage;

    @Schema(description = "結關日", example = "2025-08-12T10:00:00+08:00")
    private ZonedDateTime clearanceDate;

    @Schema(description = "領櫃代號", example = "PK12345")
    private String pickupCode;

    @Schema(description = "櫃型", example = "40HQ")
    private String containerType;

    @Schema(description = "領櫃場", example = "桃園貨櫃場")
    private String pickupYard;

    @Schema(description = "櫃號", example = "EISU1234567")
    private String containerNumber;

    @Schema(description = "交櫃場", example = "台北港堆場")
    private String deliveryYard;

    @Schema(description = "上貨地點", example = "桃園倉庫")
    private String loadingLocation;

    @Schema(description = "上貨日期", example = "2025-08-15")
    private LocalDate loadingDate;

    @Schema(description = "上貨時間", example = "14:30")
    private LocalTime loadingTime;

    @Schema(description = "備註", example = "需冷藏")
    private String remark;

    /**
     * 預設值
     */
    private OrderStatus status = OrderStatus.PENDING;
    private OrderType orderType = OrderType.EXPORT;

}
