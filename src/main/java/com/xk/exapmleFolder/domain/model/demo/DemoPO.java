package com.xk.exapmleFolder.domain.model.demo;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 📌 OrderItem.java（Entity - 訂單項目）
 * 
 * 代表訂單中的單一商品項目。
 * - `productId`：商品 ID
 * - `quantity`：數量
 * - `price`：單價
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example_order_item")
public class DemoPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private int quantity;
    private BigDecimal price;

    // **關聯回 `DemoAggregate`**
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private DemoAggregate order;
    
    // ✅ 新增帶參數的建構子
    public DemoPO(String productId, int quantity, BigDecimal price, DemoAggregate order) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public void setOrder(DemoAggregate order) {
        this.order = order;
    }

}
