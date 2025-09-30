package com.xk.car.application.model;

import lombok.Data;

@Data
public class VehicleMaintenanceResponse {

    private String uuid;
    private String carId;
    private String vehicleType;
    private String maintenanceType;
    private String mileageAt;
    private String maintenanceDate;
    private String description;
    private String cost;
    private String reminderType;
    private String nextDueMileage;
    private String nextDueDate;

    private String deleted;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
