package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class OrderUpdateStatusCmd {
    private OrderStatus status;
    private UUID operatorId;
    private LocalDate timestamp;
}
