package com.xk.car.application.model;

import com.xk.car.domain.model.enums.VehicleStatusEnum;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class VehicleStatusLogsCmd {

    private UUID uuid;
    private String carId;
    private String driverId;
    private VehicleStatusEnum status;
    private Integer operatorId;
    private String statusNote;
    private String createdBy;
    private String updatedBy;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private ZonedDateTime deletedTime;
    private Boolean deleted = false;

}
