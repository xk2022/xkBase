package com.xk.exapmleFolder.domain.model.demo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 📌 DemoBO（業務物件 - Business Object）
 * 
 * - 用於封裝 `DemoAggregate` 的業務計算（例如：計算總金額、折扣等）。
 * - 提供聚合內部邏輯，避免 `Aggregate` 過度膨脹。
 *
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
public class DemoBO {

    /**
     * 📌 【計算總金額】（僅包含有效商品）
     */
    public static BigDecimal calculateTotalAmount(List<DemoPO> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 📌 【檢查訂單是否包含指定商品】
     */
    public static boolean containsProduct(List<DemoPO> items, String productId) {
        return items.stream().anyMatch(item -> item.getProductId().equals(productId));
    }

    /**
     * 📌 【獲取訂單中的指定商品】
     */
    public static Optional<DemoPO> findProduct(List<DemoPO> items, String productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();
    }
    
}
