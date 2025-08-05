package com.xk.tom.domain.model.enums;

import lombok.Getter;

/**
 * 📌 OrderStatus (訂單狀態)
 * <p>
 * - PENDING：待處理
 * - ASSIGNED：已指派
 * - IN_TRANSIT：運送中
 * - COMPLETED：已完成
 * - CANCELLED：已取消
 *
 * @author yuan Created on 2025/08/04.
 */
@Getter
public enum OrderStatus {

    PENDING("pending", "待處理"),
    ASSIGNED("assigned", "已指派"),
    IN_TRANSIT("in_transit", "運送中"),
    COMPLETED("completed", "已完成"),
    CANCELLED("cancelled", "已取消");

    private final String code;
    private final String label;

    OrderStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 透過 code 找狀態（方便前端傳參數用）
     */
    public static OrderStatus fromCode(String code) {
        for (OrderStatus status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown OrderStatus code: " + code);
    }

}
