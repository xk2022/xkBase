package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 ExportOrderRequestDto
 * - 出口訂單 API 輸入資料結構 (Request DTO)
 * - 用於 Create / Update 出口訂單
 * <p>
 * ⚠️ 注意：
 * - 建立時 uuid 可以為 null（由系統產生）
 * - 更新時 uuid 必填（指定要更新的訂單）
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
@Schema(description = "出口訂單請求 DTO")
public class ExportOrderRequestDto {

    @Schema(description = "訂單 UUID（更新必填，建立可忽略）")
    private UUID uuid;

    @NotNull(message = "客戶 ID 不可為空")
    @Schema(description = "客戶 ID", example = "CUST123")
    private String customerId;

    @Schema(description = "出口日期", example = "2025-08-10T08:00:00+08:00")
    private ZonedDateTime shipDate;

    @NotNull(message = "船公司不可為空")
    @Size(max = 100)
    @Schema(description = "船公司", example = "Evergreen")
    private String shippingCompany;

    @NotNull(message = "船名/航次不可為空")
    @Size(max = 100)
    @Schema(description = "船名/航次", example = "YM Wellness / 088W")
    private String vesselVoyage;

    @Schema(description = "結關日", example = "2025-08-15T18:00:00+08:00")
    private ZonedDateTime clearanceDate;

    @Schema(description = "領櫃代號", example = "PK123456")
    private String pickupCode;

    @Schema(description = "櫃型", example = "40HQ")
    private String containerType;

    @Schema(description = "領櫃場", example = "桃園貨櫃場")
    private String pickupYard;

    @Schema(description = "櫃號", example = "EISU1234567")
    private String containerNumber;

    @Schema(description = "交櫃場", example = "高雄港 CY 區")
    private String deliveryYard;

    @Schema(description = "上貨地點", example = "新竹工廠")
    private String loadingLocation;

    @Schema(description = "上貨日期", example = "2025-08-12T09:00:00+08:00")
    private ZonedDateTime loadingDate;

    @Schema(description = "上貨時間", example = "2025-08-12T10:30:00+08:00")
    private ZonedDateTime loadingTime;

    @Schema(description = "備註", example = "需要冷藏櫃")
    private String remark;

    @Schema(description = "訂單狀態 (系統自動帶入，建立時可略過)", example = "PENDING")
    private OrderStatus status = OrderStatus.PENDING;

}
