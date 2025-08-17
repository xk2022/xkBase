package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class VihicleBo {

    private UUID uuid;
    private VehicleEnum vehicleType;
    private String licensePlate;
    private String brandModel;
    private String year;
    private BigDecimal mileage;
    private VehicleStatusEnum status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
