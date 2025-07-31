package com.xk.exapmleFolder.domain.model.demo;

/**
 * 📌 OrderStatusEnum（ENUM - 訂單狀態）
 * 
 * 定義訂單的狀態及可執行的狀態轉換：
 * - `PENDING`（待處理）
 * - `PLACED`（已下單）
 * - `SHIPPED`（已發貨）
 * - `DELIVERED`（已送達）
 * - `CANCELLED`（已取消）
 * - `COMPLETED`（已完成）
 * 
 * 支援：
 * - **isCancellable()**：是否允許取消
 * - **isUpdatable()**：是否允許修改
 * - **nextStatus()**：獲取下一個狀態
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
public enum OrderStatusEnum {

    PENDING,       // ✅ 待處理
    PLACED,        // ✅ 已下單
    SHIPPED,       // ✅ 已發貨
    DELIVERED,     // ✅ 已送達
    COMPLETED,     // ✅ 已完成
    CANCELLED;     // ❌ 已取消（終結狀態）

    /**
     * 📌 是否允許取消訂單？
     * - **PENDING / PLACED** 可以取消
     * - **SHIPPED / DELIVERED / COMPLETED** 不能取消
     * - **CANCELLED** 已取消，不能再變更
     */
    public boolean isCancellable() {
        return this == PENDING || this == PLACED;
    }

    /**
     * 📌 是否允許修改訂單？
     * - **PENDING / PLACED** 可以修改
     * - **SHIPPED / DELIVERED / COMPLETED** 不能修改
     * - **CANCELLED** 不能修改
     */
    public boolean isUpdatable() {
        return this == PENDING || this == PLACED;
    }

    /**
     * 📌 取得訂單的下一個狀態（根據當前狀態）
     */
    public OrderStatusEnum nextStatus() {
        return switch (this) {
            case PENDING -> PLACED;
            case PLACED -> SHIPPED;
            case SHIPPED -> DELIVERED;
            case DELIVERED -> COMPLETED;
            default -> throw new IllegalStateException("⚠️ 該狀態不可再變更：" + this);
        };
    }

}
