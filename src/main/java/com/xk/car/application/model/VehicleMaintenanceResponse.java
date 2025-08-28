package com.xk.car.application.model;

import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class VehicleMaintenanceResponse {

    private String uuid;
    private String carId;

    private String maintenanceType;
    private String mileageAt;
    private String maintenanceDate;
    private String description;
    private String cost;
    private String reminderType;
    private String nextDueMileage;
    private String nextDueDate;

    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
