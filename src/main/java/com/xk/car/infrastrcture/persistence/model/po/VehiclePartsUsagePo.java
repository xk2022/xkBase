package com.xk.car.infrastrcture.persistence.model.po;


import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;
import java.util.UUID;

/**
 * 耗損與維修項目紀錄
 * hank create 8/11
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_parts_usage")
@Entity
@SQLDelete(sql = "UPDATE vehicle_parts_usage  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class VehiclePartsUsagePo extends SoftDeletableEntity implements Serializable {



    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
    private UUID uuid;

    @Column(name = "car_id", nullable = false)
    @Comment("關聯車輛")
    private String carId;

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
