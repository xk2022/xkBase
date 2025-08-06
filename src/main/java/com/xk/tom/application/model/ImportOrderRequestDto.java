package com.xk.tom.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * 📌 ImportOrderRequestDto
 * - API 層請求物件（進口訂單）
 * - 用於建立 / 更新 進口訂單
 * <p>
 * 👉 Controller → UseCase 的傳輸資料
 * 👉 UseCase 會轉換成 Command/Entity
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderRequestDto {

    @Schema(description = "訂單 UUID（更新時必填，建立時可為 null）")
    private UUID uuid;

    @Schema(description = "客戶 ID", example = "1001")
    @NotNull(message = "客戶 ID 不能為空")
    private Long customerId;

    @Schema(description = "進口日期", example = "2025-08-05")
    @NotNull(message = "進口日期必填")
    private LocalDate shipDate;

    @Schema(description = "船公司", example = "OOCL")
    @NotBlank(message = "船公司必填")
    private String shippingCompany;

    @Schema(description = "船名/航次", example = "Evergreen / 123A")
    @NotBlank(message = "船名/航次必填")
    private String vesselVoyage;

    @Schema(description = "櫃號", example = "TCNU1234567")
    @NotBlank(message = "櫃號必填")
    private String containerNumber;

    @Schema(description = "櫃型", example = "40HQ")
    @NotBlank(message = "櫃型必填")
    private String containerType;

    @Schema(description = "櫃場", example = "台北港堆場 A")
    @NotBlank(message = "櫃場必填")
    private String containerYard;

    @Schema(description = "領櫃期限", example = "2025-08-10")
    private LocalDate lastPickupDate;

    @Schema(description = "提貨單號 (DO)", example = "DO-20250805-001")
    private String doNumber;

    @Schema(description = "提貨單位置", example = "基隆關")
    private String doLocation;

    @Schema(description = "送貨地點", example = "台北內湖倉庫")
    @NotBlank(message = "送貨地點必填")
    private String deliveryLocation;

    @Schema(description = "送貨日期", example = "2025-08-12")
    private LocalDate deliveryDate;

    @Schema(description = "送貨時間", example = "14:30")
    private LocalTime deliveryTime;

    @Schema(description = "還櫃地點", example = "台北港堆場 B")
    private String returnYard;

    @Schema(description = "還櫃日期", example = "2025-08-15")
    private LocalDate returnDate;

    @Schema(description = "還櫃時間", example = "10:00")
    private LocalTime returnTime;

    @Schema(description = "備註")
    private String remark;

}
