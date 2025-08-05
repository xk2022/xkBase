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
    private ZonedDateTime deletedTime;
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

    /**
     * 狀態變更邏輯
     */
    public void updateStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("已完成訂單不可再更新狀態");
        }
        this.status = newStatus;
    }

    /**
     * 初始化訂單
     */
    public void initialize() {
        this.orderType = "import";
        this.status = OrderStatus.PENDING;
    }

}
