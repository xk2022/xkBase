package com.xk.car.domain.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 *車頭與板車管理
 * Hank create 8/31
 */
@Data
public class VehiclePairingsEntity {

    private UUID uuid;
    private String headId;
    private String trailerId;
    private ZonedDateTime bindTime;
    private ZonedDateTime unbindTime;
    private String bindBy;
    private String unbindBy;
    private String note;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private LocalDateTime deletedTime;
    private Boolean deleted = false;
    private String createdBy;
    private String updatedBy;

    public void initialize() {
        this.createdTime = ZonedDateTime.now();
    }
}
