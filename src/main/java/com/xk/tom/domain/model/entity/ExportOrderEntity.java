package com.xk.tom.domain.model.entity;

import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 ExportOrderEntity (出口訂單聚合根)
 * - 負責出口訂單的狀態與業務邏輯
 * - 與資料庫 PO 分離
 * <p>
 * ⚠️ 注意：
 * - 僅處理業務邏輯，不做 ORM 設定
 * - 資料存取由 Repository/Infra 層負責
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ExportOrderEntity {

    private Long orderId;
    private UUID uuid;
    private String orderType = "export"; // 固定為出口訂單
    private String customerId;
    private OrderStatus status = OrderStatus.PENDING;
    private Boolean deleted = false;
    private LocalDateTime deletedTime;

    // 出口專屬欄位
    private LocalDate shipDate;          // 出口日期
    private String shippingCompany;      // 船公司
    private String vesselVoyage;         // 船名/航次
    private LocalDate clearanceDate;     // 結關日
    private String pickupCode;           // 領櫃代號
    private String containerType;        // 櫃型
    private String pickupYard;           // 領櫃場
    private String containerNumber;      // 櫃號
    private String deliveryYard;         // 交櫃場
    private String loadingLocation;      // 上貨地點
    private LocalDate loadingDate;   // 上貨日期
    private LocalTime loadingTime;   // 上貨時間
    private String remark;               // 備註

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    /**
     * 初始化訂單
     */
    public void initialize() {
        this.orderType = "export";
        this.status = OrderStatus.PENDING;
        this.createdTime = LocalDateTime.now();
    }

    /**
     * 更新狀態（含檢查規則）
     */
    public void updateStatus(OrderStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    String.format("非法狀態轉換: %s → %s", this.status, newStatus)
            );
        }
        this.status = newStatus;
        this.updatedTime = LocalDateTime.now();
    }

    /**
     * 軟刪除
     */
    public void delete() {
        if (this.status != OrderStatus.PENDING) {
            throw new IllegalStateException("只有 PENDING 狀態的訂單可以刪除");
        }
        this.deleted = true;
        this.deletedTime = LocalDateTime.now();
    }

    /**
     * 恢復訂單
     */
    public void restore() {
        if (!Boolean.TRUE.equals(this.deleted)) {
            throw new IllegalStateException("訂單不是刪除狀態，無法恢復");
        }
        this.deleted = false;
        this.deletedTime = null;
        this.status = OrderStatus.PENDING;
    }

    public void assign(UUID vehicleId, UUID driverId, UUID assignedBy, ZonedDateTime assignedAt) {
    }

    public void updateStatus(OrderStatus newStatus, UUID operatorId, LocalDate timestamp) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    String.format("非法的狀態轉換: %s → %s", this.status, newStatus)
            );
        }
        this.status = newStatus;
//        this.lastUpdatedBy = operatorId;
//        this.lastUpdatedAt = timestamp;
    }
}
