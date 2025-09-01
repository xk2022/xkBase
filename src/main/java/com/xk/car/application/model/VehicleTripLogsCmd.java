package com.xk.car.application.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class VehicleTripLogsCmd {
    private UUID uuid;
    private String carId;
    private String orderId;
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
}
