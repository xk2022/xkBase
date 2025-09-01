package com.xk.car.application.model;

import lombok.Data;

@Data
public class VehicleTripLogsRequest {
    private String uuid;
    private String carId;
    private String orderId;
    private String startMileage;
    private String endMileage;
    private String distance;
    private String date;
    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
