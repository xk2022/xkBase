package com.xk.exapmleFolder.application.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 OrderItemDTO（資料傳輸物件 - DTO）
 * 
 * 主要用於 API 層與應用層之間的數據傳遞。
 * - `productId`：商品 ID
 * - `quantity`：數量（必須 >= 1）
 * - `price`：單價（必須 >= 0）
 * 
1️⃣ 透過 Builder 來建構物件
DemoItemDTO item = DemoItemDTO.builder()
        .productId("P123")
        .quantity(2)
        .price(new BigDecimal("10.50"))
        .build();
2️⃣ toBuilder() 修改現有物件
DemoItemDTO updatedItem = item.toBuilder()
        .quantity(5)  // 修改數量
        .build();
3️⃣ 確保數據合法
try {
    item.validate(); // 檢查數據是否正確
} catch (IllegalArgumentException e) {
    System.err.println(e.getMessage()); // 捕獲錯誤
}
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 - 增加數據驗證 & toBuilder 支援
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // ✅ 支援 `toBuilder()`
@EqualsAndHashCode // ✅ 確保 `Set`、`Map` 內的比較正確
@ToString // ✅ 方便日誌記錄
public class DemoItemDTO {
    
    private String productId;
    private int quantity;
    private BigDecimal price;

    /**
     * 📌 數據驗證（確保數量與價格有效）
     */
    public void validate() {
        if (quantity < 1) {
            throw new IllegalArgumentException("❌ 商品數量必須 >= 1");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("❌ 商品價格不能為負數");
        }
    }
    
}
