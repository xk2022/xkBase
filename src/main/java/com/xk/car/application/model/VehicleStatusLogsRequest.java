package com.xk.car.application.model;

import lombok.Data;

/**
 * hank create on 2025/09/01
 */
@Data
public class VehicleStatusLogsRequest {

    private String uuid;
    private String carId;
    private String driverId;
    private String status;
    private String operatorId;
    private String statusNote;
}
