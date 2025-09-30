package com.xk.car.application.model;

import lombok.Data;

/**
 * 車輛里程耗損紀錄 回應參數
 * @author hank created 2025/08/15
 */
@Data
public class VehicleTripLogsResponse {

    private String uuid;
    private String carId;
    private String orderId;
    private String vehicleType;
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
