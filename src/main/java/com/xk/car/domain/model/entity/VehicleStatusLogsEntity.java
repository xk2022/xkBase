package com.xk.car.domain.model.entity;


import com.xk.car.domain.model.enums.VehicleStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;


/**
 *車輛狀態管理
 * Hank create 8/11
 */
@Data
public class VehicleStatusLogsEntity {

    private UUID uuid;
    private String carId;
    private String driverId;
    private VehicleStatusEnum status;
    private Integer operatorId;
    private String statusNote;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;

    public void initialize() {
        this.createdTime = ZonedDateTime.now();
    }
}
