package com.xk.car.application.model;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class VehicleResponse {

    private String vehicleType;
    private String licensePlate;
    private String brandModel;
    private String year;
    private String mileage;
    private String status;

    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
