package com.xk.tom.infra.persistence.model.po;

import com.xk.common.base.SoftDeletableEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.model.enums.OrderType;
import com.xk.tom.domain.model.enums.OrderTypeConverter;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

/**
 * 📌 OrderPo（抽象父類，持久化對象）
 * <p>
 * - 對應資料表：`orders`（單表繼承策略）
 * - 子類別：`ImportOrderPo`, `ExportOrderPo`
 * - 用途：
 * - 基礎欄位（orderId、uuid、customerId、status、刪除標記）
 * - 提供 **軟刪除（Soft Delete）** 支援
 * - 作為 JPA 單表繼承的基底類別
 * <p>
 * ⚠️ 注意：
 * - `uuid` 於 `@PrePersist` 自動生成，不允許更新
 * - 狀態預設為 `PENDING`
 * - 刪除時會自動設定 `deleted = true` 並更新 `delete_time`
 *
 * @author yuan Created on 2025/08/04.
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 單表繼承
@DiscriminatorColumn(name = "order_type")               // 區分欄位
@SQLDelete(sql = "UPDATE orders SET deleted = 1, delete_time = NOW() WHERE order_id = ?")
public abstract class OrderPo extends SoftDeletableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    @Comment("00_流水號")
    private Long orderId;

    @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
    private UUID uuid;

    @Convert(converter = OrderTypeConverter.class)
    @Column(name = "order_type", nullable = false, length = 10, insertable = false, updatable = false)
    @Comment("訂單類型：import / export")
    private OrderType orderType;

    @Column(name = "customer_id")
    @Comment("客戶 ID")
    private String customerId;

//    @Column(name = "date")
//    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Comment("訂單狀態")
    private OrderStatus status = OrderStatus.PENDING;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

}
