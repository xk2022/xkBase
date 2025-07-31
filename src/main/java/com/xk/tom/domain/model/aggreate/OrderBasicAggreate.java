package com.xk.tom.domain.model.aggreate;


import com.xk.common.base.BaseEntity;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class OrderBasicAggreate implements Serializable {


    private static final long serialVersionUID = 1L;


    @Column(name = "order_type")
    @Comment("訂單類型")
    @Enumerated(EnumType.STRING)
    private OrderTypeEnum orderType;


    @Column(name = "customer_id")
    @Comment("客戶 ID")
    private Long customerId;


    @Column(name = "status")
    @Comment("訂單狀態")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;


    @Schema(description = "創建者", required = true, example = "admin")
    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 50)
    @Comment("創建者")
    private String createdBy;

    @Schema(description = "創建時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("創建時間")
    private ZonedDateTime createdTime;

    @Schema(description = "編輯者", required = true, example = "admin")
    @LastModifiedBy
    @Column(name = "update_by")
    @Comment("編輯者")
    private String updatedBy;

    @Schema(description = "編輯時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @UpdateTimestamp
    @Column(name = "update_time", nullable = true) // Make the column nullable
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("編輯時間")
    private ZonedDateTime updatedTime;


}
