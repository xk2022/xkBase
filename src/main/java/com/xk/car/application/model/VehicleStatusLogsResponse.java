package com.xk.car.application.model;

import com.xk.car.domain.model.enums.VehicleEnum;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 *  車輛狀態管理
 *   @author hank Created on 2025/08/29.
 */
@Data
public class VehicleStatusLogsResponse {


    private String carId;
    private String driverId;
    private String vehicleType;
    private String status;
    private String operatorId;
    private String statusNote;
    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;

}
