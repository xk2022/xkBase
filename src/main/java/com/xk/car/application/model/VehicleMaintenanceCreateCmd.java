package com.xk.car.application.model;

import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import com.xk.car.domain.model.enums.VehicleEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class VehicleMaintenanceCreateCmd {

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
    private String createdBy;
    private String updatedBy;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private ZonedDateTime deletedTime;
}
