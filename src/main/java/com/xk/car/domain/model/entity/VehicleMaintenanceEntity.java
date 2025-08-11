package com.xk.car.domain.model.entity;

import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;


/**
 * 車輛性能監控與維修提醒
 * Hank create 8/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Vehicle_Maintenance")
@Entity
public class VehicleMaintenanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Comment("流水號")
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    @Comment("關聯車輛")
    private String vehicleId;


    @Column(name = "maintenance_type", nullable = false)
    @Comment("維修類型")
    private MaintenanceTypeEnum maintenanceType;


    @Column(name = "mileage_at")
    @Comment("維修時的里程")
    private BigDecimal mileageAt;


    @Column(name = "date", nullable = false)
    @Comment("維修日期")
    private Date maintenanceDate;


    @Column(name = "description", nullable = false)
    @Comment("維修項目或情況說明")
    private String description;


    @Column(name = "cost")
    @Comment("維修金額")
    private BigDecimal cost;

    @Column(name = "reminder_type")
    @Comment("提醒方式")
    private ReminderTypeEnum reminderType;

    @Column(name = "next_due_mileage")
    @Comment("下次預定保養里程")
    private BigDecimal nextDueMileage;

    @Column(name = "next_due_date")
    @Comment("下次預定保養時間")
    private Date nextDueDate;

    @Schema(description = "創建時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false , nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("創建時間")
    private ZonedDateTime createdAt;


}
