package com.xk.exapmleFolder.domain.model.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.xk.exapmleFolder.application.model.DemoItemDTO;
import com.xk.exapmleFolder.domain.event.DemoPlacedEvent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 Order.java（聚合根 - Aggregate Root）
 * 
 * - 代表訂單（Order），負責訂單的 **業務邏輯** 與 **狀態管理**
 * - **`customerId`**：訂單客戶 ID
 * - **`items`**：訂單項目列表（關聯 `DemoPO`）
 * - **`status`**：訂單當前狀態
 * - ✅ `placeOrder()`：提交訂單
 * - ✅ `updateOrder()`：更新訂單內容
 * - ✅ `cancelOrder()`：取消訂單
 *
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor // ✅ 無參構造函式（JPA 需要）
@AllArgsConstructor // ✅ 有參構造函式
@Builder // ✅ 支援 `Builder` 模式
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // ✅ 避免 Hibernate 過度使用 equals()
@Table(name = "example_order")
public class DemoAggregate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // 避免 Hibernate 過度使用 equals() 和 hashCode()
	private Long id;

	private String customerId; // **外部關聯應該用 ID，而不是關聯對象**

	@Enumerated(EnumType.STRING)
	private OrderStatusEnum status;

    // **關聯訂單項目**
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default // ✅ 預設初始化
    @ToString.Exclude // ✅ 防止 `toString()` 遞迴問題
    private List<DemoPO> items = new ArrayList<>();

    // **🔹 建構子（強制初始狀態）**
    public DemoAggregate(String customerId) {
        this.customerId = customerId;
        this.status = OrderStatusEnum.PENDING;
    }

    // ✅ 新增商品
    public void addItem(DemoPO item) {
        item.setOrder(this); // **確保雙向關聯**
        this.items.add(item);
    }

    // ✅ 【訂單提交】變更狀態並觸發事件
    public DemoPlacedEvent placeOrder() {
        checkOrderItems(); // ✅ 確保訂單有商品
        this.status = OrderStatusEnum.PLACED; // ✅ 變更狀態
        return createEvent(); // ✅ 產生事件（保持單一職責）
    }
    /**
     * 📌 【業務檢查】確保訂單內有商品
     */
    // ✅ 【輔助方法】確保訂單有效
    private void checkOrderItems() {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("📌 訂單必須至少包含一項商品");
        }
    }
    /**
     * 📌 【事件產生】DemoPlacedEvent
     */
    private DemoPlacedEvent createEvent() {
        return DemoPlacedEvent.of(this.id, this.customerId);
    }

    // ✅ 檢查是否已完成
    public boolean isCompleted() {
        return this.status == OrderStatusEnum.COMPLETED;
    }

    // ✅ **更新訂單**（確保線程安全）
    public synchronized void updateOrder(List<DemoItemDTO> newItems) {
        if (newItems == null || newItems.isEmpty()) {
            throw new IllegalArgumentException("📌 訂單項目不能為空");
        }

        // 🔹 轉換 `newItems` 為 Map，以 `productId` 作為 Key，方便查找
        Map<String, DemoItemDTO> newItemMap = newItems.stream()
                .collect(Collectors.toMap(DemoItemDTO::getProductId, item -> item));

        // 🔹 **更新已有的 `items`**
        Iterator<DemoPO> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            DemoPO existingItem = iterator.next();
            DemoItemDTO newItem = newItemMap.remove(existingItem.getProductId());

            if (newItem != null) {
                // ✅ **更新數量與價格**
                existingItem.setQuantity(newItem.getQuantity());
                existingItem.setPrice(newItem.getPrice());
            } else {
                // ❌ **刪除不存在的項目**
                iterator.remove();
            }
        }

        // 🔹 **新增 `newItems` 中不存在於 `items` 的項目**
        newItemMap.values().forEach(item -> {
            DemoPO newItem = new DemoPO(null, item.getProductId(), item.getQuantity(), item.getPrice(), this);
            newItem.setOrder(this); // **確保關聯**
            this.items.add(newItem);
        });
    }

    // ✅ 取消訂單
    public void cancel() {
        if (this.status == OrderStatusEnum.COMPLETED) {
            throw new IllegalStateException("📌 訂單已完成，無法取消");
        }
        this.status = OrderStatusEnum.CANCELLED;
    }
    
    public BigDecimal getTotalAmount() {
        return DemoBO.calculateTotalAmount(this.items);
    }


}
