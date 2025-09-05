package com.xk.car.infrastrcture.persistence.model.po;


import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.common.base.SoftDeletableEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * 車輛性能監控與維修提醒
 * Hank create 8/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vehicle_maintenance")
@Entity
@SQLDelete(sql = "UPDATE vehicle_maintenance  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class VehicleMaintenancePo extends SoftDeletableEntity implements Serializable {


    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
    private UUID uuid;


    @Column(name = "car_id", nullable = false)
    @Comment("關聯車輛")
    private String carId;

    @NotNull
    @Column(name = "vehicle_type" ,nullable = false)
    @Comment("車頭/板車")
    private VehicleEnum vehicleType;


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

}
