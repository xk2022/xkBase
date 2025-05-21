package com.xk.tom.domain.model.aggreate;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;


@Data
@Table(name="Order_Logs")
@Entity
public class OrderLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLogId", updatable = false, nullable = false)
    @Comment("流水號") // 描述
    private Long logId;

    @Column(name = "orderId" , nullable = false)
    private String orderId;
}
