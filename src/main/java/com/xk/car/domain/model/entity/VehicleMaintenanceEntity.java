package com.xk.car.domain.model.entity;

import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;


/**
 * 車輛性能監控與維修提醒
 * Hank create 8/11
 */
@Data
public class VehicleMaintenanceEntity {

    private UUID uuid;
    private String carId;
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

    /**
     * 初始化車輛提醒監控
     */
    public void initialize(){
        this.createdTime = ZonedDateTime.now();
    }



}
