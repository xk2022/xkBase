package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.VehicleStatusEnum;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class VehicleStatusLogsBo {

    private UUID uuid;
    private String carId;
    private VehicleStatusEnum status;
    private Integer operatorId;
    private String statusNote;
    private Boolean deleted = false;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;


}
