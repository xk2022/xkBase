package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 📌 ExportOrderQueryDto
 * - 出口訂單查詢條件
 * - 提供 UseCase / Service 進行複合條件查詢
 * <p>
 * 使用場景：
 * - /api/export-orders?status=ASSIGNED&startDate=2025-08-01&endDate=2025-08-15
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
public class ExportOrderQueryDto {

    @Schema(description = "出口訂單 UUID")
    private UUID uuid;

    @Schema(description = "客戶 ID", example = "10001")
    private Long customerId;

    @Schema(description = "訂單狀態", example = "PENDING")
    private OrderStatus status;

    @Schema(description = "船公司 (模糊查詢)", example = "EVERGREEN")
    private String shippingCompany;

    @Schema(description = "船名/航次 (模糊查詢)", example = "YM WARMTH / 123E")
    private String vesselVoyage;

    @Schema(description = "櫃號 (模糊查詢)", example = "EISU1234567")
    private String containerNumber;

    @Schema(description = "交櫃場", example = "台北港堆場")
    private String deliveryYard;

    @Schema(description = "上貨地點", example = "桃園倉庫")
    private String loadingLocation;

    @Schema(description = "開始日期 (出口日期)", example = "2025-08-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Schema(description = "結束日期 (出口日期)", example = "2025-08-31")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

}
