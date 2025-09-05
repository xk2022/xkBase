package com.xk.car.domain.model.entity;


import com.xk.car.domain.model.enums.VehicleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * 車輛里程
 * create by hank
 */
@Data
public class VehicleTripLogsEntity {

    private UUID uuid;
    private String carId;
    private String orderId;
    private VehicleEnum vehicleType;
    private BigDecimal startMileage;
    private BigDecimal endMileage;
    private BigDecimal distance;
    private Date date;
    private Boolean deleted;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;

    public void initialize() {
        this.createdTime = ZonedDateTime.now();
    }
}
