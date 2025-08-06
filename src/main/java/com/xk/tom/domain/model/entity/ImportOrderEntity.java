package com.xk.tom.domain.model.entity;

import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.model.vo.ContainerNumber;
import com.xk.tom.domain.model.vo.DeliveryLocation;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * 📌 ImportOrderEntity (進口訂單聚合根)
 * - 負責進口訂單的狀態邏輯
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderEntity {

    private Long orderId;
    private UUID uuid;
    private String orderType;
    private String customerId;
    private LocalDate date;
    private OrderStatus status = OrderStatus.PENDING;
    private Boolean deleted = false;
    private LocalDate deletedTime;
    private LocalDate shipDate;
    private String shippingCompany;
    private String vesselVoyage;
    private String containerNo;
    private String containerType;
    private String containerYard;
    private ContainerNumber containerNumber;
    private ZonedDateTime lastPickupDate;
    private String deliveryOrder;
    private String deliveryOrderLocation;
    private DeliveryLocation deliveryLocation;
    private ZonedDateTime deliveryDateTime;
    private String returnYard;
    private ZonedDateTime returnDateTime;
    private String remark;
    private ZonedDateTime updatedTime;

    /**
     * 初始化訂單
     */
    public void initialize() {
        this.orderType = "import";
        this.status = OrderStatus.PENDING;
    }

    /**
     * 狀態變更邏輯
     */
    public void updateStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("已完成訂單不可再更新狀態");
        }
        this.status = newStatus;
    }

    public void assign(UUID vehicleId, UUID driverId, UUID assignedBy, LocalDate assignedAt) {
        if (this.status != OrderStatus.PENDING) {
            throw new IllegalStateException("只有 PENDING 訂單可以指派");
        }
        // TODO
//        this.assignedVehicleId = vehicleId;
//        this.assignedDriverId = driverId;
//        this.assignedBy = assignedBy;
//        this.assignedAt = assignedAt;
        this.status = OrderStatus.ASSIGNED;
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

    public void restore() {
        if (!Boolean.TRUE.equals(this.deleted)) {
            throw new IllegalStateException("訂單不是刪除狀態，不能恢復");
        }
        this.deleted = false;
        this.deletedTime = null;
        this.status = OrderStatus.PENDING; // 規則：恢復後回到待處理
    }

    public void delete() {
        if (this.status != OrderStatus.PENDING) {
            throw new IllegalStateException("只有 PENDING 狀態的訂單可以刪除");
        }
        this.deleted = true;
        this.deletedTime = LocalDate.now();
    }


}
