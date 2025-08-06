package com.xk.tom.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * 📌 ExportOrderResponseDto
 * - 出口訂單 Response DTO
 * - 用於 API 回傳出口訂單的完整資訊
 * <p>
 * 共用欄位: OrderResponseDto (orderId, uuid, customerId, status...)
 * 專屬欄位: 出口日期、船公司、航次、結關日、櫃號、交櫃場、上貨資訊
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
public class ExportOrderResponseDto {

    @Schema(description = "出口日期", example = "2025-08-06")
    private ZonedDateTime shipDate;

    @Schema(description = "船公司", example = "EVERGREEN")
    private String shippingCompany;

    @Schema(description = "船名/航次", example = "YM WARMTH / 123E")
    private String vesselVoyage;

    @Schema(description = "結關日", example = "2025-08-10")
    private ZonedDateTime clearanceDate;

    @Schema(description = "領櫃代號", example = "PICK123")
    private String pickupCode;

    @Schema(description = "櫃型", example = "40HQ")
    private String containerType;

    @Schema(description = "領櫃場", example = "基隆港堆場")
    private String pickupYard;

    @Schema(description = "櫃號", example = "EISU1234567")
    private String containerNumber;

    @Schema(description = "交櫃場", example = "台北港倉庫")
    private String deliveryYard;

    @Schema(description = "上貨地點", example = "桃園倉庫")
    private String loadingLocation;

    @Schema(description = "上貨日期", example = "2025-08-12")
    private LocalDate loadingDate;

    @Schema(description = "上貨時間", example = "2025-08-12T15:00:00+08:00")
    private ZonedDateTime loadingTime;

    @Schema(description = "備註", example = "急件，優先處理")
    private String remark;
}
