package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;

/**
 * 📌 ImportOrderQueryDto
 * - 查詢進口訂單的查詢條件
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderQueryDto {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String deliveryLocation;
    private OrderStatus ststus;
}