package com.xk.exapmleFolder.domain.event;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 📌 OrderPlacedEvent.java - 訂單建立事件（Event）
 * 
 * ✅ **不可變（Immutable）**
 * ✅ **可序列化（Serializable）**
 * ✅ **唯一性（@EqualsAndHashCode）**
 * ✅ **事件發生時間（timestamp）**
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 - 改進事件設計
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DemoPlacedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long orderId;
    private final String customerId;
    private final Instant timestamp; // **事件發生時間**

    /**
     * 📌 事件工廠方法（確保事件時間統一）
     * 
// ✅ 透過 `of()` 來建立事件
DemoPlacedEvent event = DemoPlacedEvent.of(orderId, customerId);
// ✅ 發送事件
eventPublisher.publishEvent(event);
     */
    public static DemoPlacedEvent of(Long orderId, String customerId) {
        return new DemoPlacedEvent(orderId, customerId, Instant.now());
    }

}
