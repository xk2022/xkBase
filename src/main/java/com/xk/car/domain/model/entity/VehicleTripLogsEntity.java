package com.xk.car.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 車輛里程
 * create by hank
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Vehicle_Trip_Logs")
@Entity
public class VehicleTripLogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Comment("流水號主鍵")
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    @Comment("車輛ID")
    private String vehicleId;

    @Column(name = "order_id", nullable = false)
    @Comment("對應派車單編號")
    private String orderId;


    @Column(name = "start_mileage", nullable = false)
    @Comment("出發里程")
    private BigDecimal startMileage;


    @Column(name = "end_mileage", nullable = false)
    @Comment("返回里程")
    private BigDecimal endMileage;

    @Column(name = "distance", nullable = false)
    @Comment("總行駛距離(系統計算)")
    private BigDecimal distance;


    @Column(name = "date", nullable = false)
    @Comment("行駛日期")
    private Date date;
}
