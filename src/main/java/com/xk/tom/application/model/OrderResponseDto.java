package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.model.enums.OrderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 OrderResponseDto
 * - API 層回應物件（進出口訂單共用）
 * - 扁平化設計，涵蓋共用欄位與進/出口專屬欄位
 * <p>
 * 👉 用例：
 * - ImportOrderFindUseCase / ExportOrderFindUseCase 回傳
 * - ImportOrderManageUseCase / ExportOrderManageUseCase 回傳
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class OrderResponseDto {

    // ===== 共用欄位 =====
    @Schema(description = "UUID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID uuid;

    @Schema(description = "訂單流水號", example = "20250805-0001")
    private String orderId;

    @Schema(description = "訂單類型", example = "import / export")
    private OrderType orderType;

    @Schema(description = "客戶 ID", example = "1001")
    private Long customerId;

    @Schema(description = "訂單狀態", example = "PENDING")
    private OrderStatus status;

    @Schema(description = "建立時間")
    private ZonedDateTime createdTime;

    @Schema(description = "更新時間")
    private ZonedDateTime updatedTime;

    @Schema(description = "刪除標記")
    private Boolean deleted;

    // ===== 出口專屬 =====
    @Schema(description = "出口日期")
    private LocalDate exportShipDate;

    @Schema(description = "結關日")
    private LocalDate clearanceDate;

    @Schema(description = "領櫃代號")
    private String pickupCode;

    @Schema(description = "領櫃場")
    private String pickupYard;

    @Schema(description = "交櫃場")
    private String deliveryYard;

    @Schema(description = "上貨地點")
    private String loadingLocation;

    @Schema(description = "上貨日期")
    private LocalDate loadingDate;

    @Schema(description = "上貨時間")
    private LocalTime loadingTime;

    // ===== 進口專屬 =====
    @Schema(description = "進口日期")
    private LocalDate importShipDate;

    @Schema(description = "提貨單號 (DO)")
    private String doNumber;

    @Schema(description = "提貨單位置")
    private String doLocation;

    @Schema(description = "送貨地點")
    private String deliveryLocation;

    @Schema(description = "送貨日期")
    private LocalDate deliveryDate;

    @Schema(description = "送貨時間")
    private LocalTime deliveryTime;

    @Schema(description = "還櫃地點")
    private String returnYard;

    @Schema(description = "還櫃日期")
    private LocalDate returnDate;

    @Schema(description = "還櫃時間")
    private LocalTime returnTime;

    // ===== 共用描述 =====
    @Schema(description = "船公司")
    private String shippingCompany;

    @Schema(description = "船名/航次")
    private String vesselVoyage;

    @Schema(description = "櫃號")
    private String containerNumber;

    @Schema(description = "櫃型")
    private String containerType;

    @Schema(description = "櫃場")
    private String containerYard;

    @Schema(description = "領櫃期限")
    private LocalDate lastPickupDate;

    @Schema(description = "備註")
    private String remark;
}
