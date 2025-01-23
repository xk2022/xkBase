package com.xk.exapmleFolder.application.model;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 📌 OrderResponseDTO（回應 DTO）
 * 
 * - `orderId`：訂單 ID
 * - `status`：訂單狀態
 * - `items`：訂單項目（不可變列表）
 * 
 * ❌ **避免 `setter()`，確保 DTO 不被修改**
 * ✅ **提供 `getItems()` 確保回應不會返回 `null`**
 *
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 - 增強數據安全性 & 確保 `items` 不為 `null`
 */
@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class DemoResponseDTO {

    private final Long orderId;
    private final String status;
    private final List<DemoItemDTO> items; // ✅ 確保回應包含訂單內的商品項目

    /**
     * 📌 確保 `items` 不為 `null`
     */
    public List<DemoItemDTO> getItems() {
        return items == null ? Collections.emptyList() : items;
    }

}
