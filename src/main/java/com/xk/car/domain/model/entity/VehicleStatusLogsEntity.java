package com.xk.car.domain.model.entity;


import com.xk.car.domain.model.enums.VehicleStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.ZonedDateTime;


/**
 *車輛狀態管理
 * Hank create 8/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Vehicle_Status_Logs")
public class VehicleStatusLogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false , updatable = false)
    @Comment("流水號")
    private Long id;


    @Column(name = "vehicle_id" , nullable = false)
    @Comment("對應車輛表的vehicle_id")
    private String vehicleId;


    @Column(name = "status" , nullable = false)
    @Comment("車輛狀態")
    private VehicleStatusEnum status;

    @Column(name = "updated_by" , nullable = false)
    @Comment("操作者 ID")
    private Integer updatedBy;

    @Column(name = "updated_at" , nullable = false)
    @Comment("狀態更新時間")
    private ZonedDateTime updatedAt;


    @Column(name = "status_note")
    @Comment("狀態說明")
    private String statusNote;
}
