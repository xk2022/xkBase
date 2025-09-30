package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * 車輛狀態管理
 * @author hank created on   2025/08/13.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class VehicleStatusLogsBo {

    private UUID uuid;
    private String carId;
    private String driverId;
    private VehicleEnum vehicleType;
    private VehicleStatusEnum status;
    private Integer operatorId;
    private String statusNote;
    private Boolean deleted = false;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;


}
