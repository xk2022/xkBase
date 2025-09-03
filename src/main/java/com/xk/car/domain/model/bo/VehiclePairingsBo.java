package com.xk.car.domain.model.bo;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 車頭與板車管理
 * hank create 8/31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class VehiclePairingsBo {

    private UUID uuid;
    private String headId;
    private String trailerId;
    private ZonedDateTime bindTime;
    private ZonedDateTime unbindTime;
    private String bindBy;
    private String unbindBy;
    private String note;
}
