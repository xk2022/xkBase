package com.xk.car.application.model;

import lombok.Data;

/**
 * 車輛基本資訊 回應參數
 * @author hank created 2025/08/2
 */
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
