package com.xk.tom.domain.model.entity;

import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 📌 ExportOrderEntity (出口訂單聚合根)
 * - 負責出口訂單的狀態邏輯
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ExportOrderEntity {

    private UUID uuid;
    private OrderStatus status;
    private String vesselVoyage;  // 船名/航次
    private LocalDate shipDate;

    public void updateStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("取消的訂單不可再更新狀態");
        }
        this.status = newStatus;
    }

}
