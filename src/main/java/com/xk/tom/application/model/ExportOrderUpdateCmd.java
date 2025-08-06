package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 ExportOrderUpdateCmd
 * - 更新出口訂單用 Command
 * - 由 Application UseCase 轉換 DTO → Cmd 後，交給 Domain Service 執行
 *
 * 規則：
 * - uuid 必須存在，才能更新
 * - 更新哪些欄位 → 由 Service 驗證是否允許修改
 *
 * @author yuan Created on 2025/08/06.
 */
@Data
public class ExportOrderUpdateCmd {

    @Schema(description = "訂單 UUID", required = true)
    private UUID uuid;  // 更新一定要有 uuid

    @Schema(description = "出口日期")
    private ZonedDateTime shipDate;

    @Schema(description = "船公司")
    private String shippingCompany;

    @Schema(description = "船名/航次")
    private String vesselVoyage;

    @Schema(description = "結關日")
    private ZonedDateTime clearanceDate;

    @Schema(description = "領櫃代號")
    private String pickupCode;

    @Schema(description = "櫃型")
    private String containerType;

    @Schema(description = "領櫃場")
    private String pickupYard;

    @Schema(description = "櫃號")
    private String containerNumber;

    @Schema(description = "交櫃場")
    private String deliveryYard;

    @Schema(description = "上貨地點")
    private String loadingLocation;

    @Schema(description = "上貨日期")
    private LocalDate loadingDate;

    @Schema(description = "上貨時間")
    private LocalTime loadingTime;

    @Schema(description = "備註")
    private String remark;

    @Schema(description = "狀態")
    private OrderStatus status; // 更新時允許修改狀態（需檢查業務規則）

    @Schema(description = "最後修改人")
    private String updatedBy;

    @Schema(description = "最後修改時間")
    private ZonedDateTime updatedTime;

}
