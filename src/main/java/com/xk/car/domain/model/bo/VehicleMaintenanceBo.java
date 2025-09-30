package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import com.xk.car.domain.model.enums.VehicleEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;


/**
 * 車輛性能監控與維修提醒
 * @author hank created 2025/08/13.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class VehicleMaintenanceBo {

    private UUID uuid;
    private String carId;
    private VehicleEnum vehicleType;
    private Boolean deleted = false;
    private MaintenanceTypeEnum maintenanceType;
    private BigDecimal mileageAt;
    private Date maintenanceDate;
    private String description;
    private BigDecimal cost;
    private ReminderTypeEnum reminderType;
    private BigDecimal nextDueMileage;
    private Date nextDueDate;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private String updatedBy;

}
