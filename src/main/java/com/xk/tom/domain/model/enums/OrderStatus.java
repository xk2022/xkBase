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
 * <p>
 * ✅ 支援：
 * - fromCode()：透過 code 找狀態
 * - canTransitionTo()：檢查狀態流轉是否合法
 *
 * @author yuan Created on 2025/08/04.
 */
@Getter
public enum OrderStatus {

    PENDING("pending", "待處理") {
        @Override
        public boolean canTransitionTo(OrderStatus target) {
            return target == ASSIGNED || target == CANCELLED;
        }
    },
    ASSIGNED("assigned", "已指派") {
        @Override
        public boolean canTransitionTo(OrderStatus target) {
            return target == IN_TRANSIT || target == CANCELLED;
        }
    },
    IN_TRANSIT("in_transit", "運送中") {
        @Override
        public boolean canTransitionTo(OrderStatus target) {
            return target == COMPLETED || target == CANCELLED;
        }
    },
    COMPLETED("completed", "已完成") {
        @Override
        public boolean canTransitionTo(OrderStatus target) {
            return false; // ✅ 終止狀態
        }
    },
    CANCELLED("cancelled", "已取消") {
        @Override
        public boolean canTransitionTo(OrderStatus target) {
            return false; // ✅ 終止狀態
        }
    };

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

    /**
     * 檢查是否允許狀態流轉
     *
     * @param target 目標狀態
     * @return 是否可轉換
     */
    public abstract boolean canTransitionTo(OrderStatus target);

}