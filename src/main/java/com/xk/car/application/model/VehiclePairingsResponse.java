package com.xk.car.application.model;

import lombok.Data;

/**
 * 車頭與板車管理
 * hank create 8/31
 */
@Data
public class VehiclePairingsResponse {

    private String headId;
    private String trailerId;
    private String bindTime;
    private String unbindTime;
    private String bindBy;
    private String unbindBy;
    private String note;
    private String deleted ;
    private String deletedTime;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
}
