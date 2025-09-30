package com.xk.car.application.model;

import com.xk.car.domain.model.enums.VehicleEnum;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class VehiclePartsUsageCmd {

    private UUID uuid;
    private String carId;
    private VehicleEnum vehicleType;
    private String partName;
    private BigDecimal mileage;
    private BigDecimal cost;
    private String description;
    private Date usedAt;
    private Boolean deleted = false;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;
}
