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
 * 耗損與維修項目紀錄
 * hank create 8/11
 */

@Data
public class VehiclePartsUsageEntity {

    private UUID uuid;
    private String carId;
    private VehicleEnum vehicleType;
    private String partName;
    private BigDecimal mileage;
    private BigDecimal cost;
    private String description;
    private Date usedAt;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private LocalDateTime deletedTime;
    private Boolean deleted = false;
    private String createdBy;
    private String updatedBy;

    /**
     * 初始化
     */
    public void initialize(){
        this.createdTime = ZonedDateTime.now();
    }




}
