package com.xk.exapmleFolder.application.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 OrderRequestDTO（請求 DTO）
 * 
 * - `customerId`：訂單客戶 ID（必填）
 * - `items`：訂單項目列表（至少包含 1 項）
 * 
 * Represents the data required to create or update an Order record.
 *
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 - 增加數據驗證 & toBuilder 支援
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // ✅ 允許 `toBuilder()` 來創建物件副本並修改部分欄位
@EqualsAndHashCode // ✅ 確保 `Set`、`Map` 內的比較正確
@ToString // ✅ 方便日誌記錄
public class DemoRequestDTO {

    private String customerId;
    private List<DemoItemDTO> items;

    /**
     * 📌 **數據驗證（確保客戶 ID 與商品項目有效）**
     */
    public void validate() {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ 客戶 ID 不能為空");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("❌ 訂單必須至少包含 1 項商品");
        }
        for (DemoItemDTO item : items) {
            item.validate(); // ✅ 呼叫 `DemoItemDTO` 的 `validate()`
        }
    }

}
