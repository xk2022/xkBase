package com.xk.car.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 耗損與維修項目紀錄
 * hank create 8/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Vehicle_Parts_Usage")
@Entity
public class VehiclePartsUsageEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false , updatable = false)
    @Comment("流水號")
    private Long id;


    @Column(name = "vehicle_id" , nullable = false)
    @Comment("對應車輛表的vehicle_id")
    private String vehicleId;

    @Column(name = "part_name" , nullable = false)
    @Comment("耗損或更換零件名稱")
    private String partName;

    @Column(name = "mileage" , nullable = false)
    @Comment("當時里程")
    private BigDecimal mileage;

    @Column(name = "cost")
    @Comment("更換或維修成本")
    private BigDecimal cost;

    @Column(name = "description")
    @Comment("備註或說明")
    private String description;

    @Column(name = "used_at" , nullable = false)
    @Comment("使用或更換日期")
    private Date usedAt;
}
