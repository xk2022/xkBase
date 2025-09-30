package com.xk.car.application.model;

import lombok.Data;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 車頭與板車管理
 * hank create 8/31
 */
@Data
public class VehiclePairingsCmd {

    private UUID uuid;
    private String headId;
    private String trailerId;
    private ZonedDateTime bindTime;
    private ZonedDateTime unbindTime;
    private String bindBy;
    private String unbindBy;
    private String note;
}
