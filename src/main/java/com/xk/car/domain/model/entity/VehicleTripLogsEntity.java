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
 * 車輛里程
 * create by hank
 */
@Data
public class VehicleTripLogsEntity {

    private Long vehicleId;
    private UUID uuid;
    private String carId;
    private String orderId;
    private BigDecimal startMileage;
    private BigDecimal endMileage;
    private BigDecimal distance;
    private Date date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
