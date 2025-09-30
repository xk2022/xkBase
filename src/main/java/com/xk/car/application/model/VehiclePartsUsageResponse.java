package com.xk.car.application.model;

import lombok.Data;

@Data
public class VehiclePartsUsageResponse {

    private String uuid;
    private String carId;
    private String vehicleType;
    private String partName;
    private String mileage;
    private String cost;
    private String description;
    private String usedAt;
    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
