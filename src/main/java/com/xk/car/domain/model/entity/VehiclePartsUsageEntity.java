package com.xk.car.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * 耗損與維修項目紀錄
 * hank create 8/11
 */

@Data
public class VehiclePartsUsageEntity {

    private Long vehicleId;
    private UUID uuid;
    private String carId;
    private String partName;
    private BigDecimal mileage;
    private BigDecimal cost;
    private String description;
    private Date usedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
