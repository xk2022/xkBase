package com.xk.car.application.model;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 車輛狀態管理
 * @author hank created 2025/08/15
 */
@Data
public class VehicleStatusLogsCmd {

    private UUID uuid;
    private String carId;
    private String driverId;
    private VehicleEnum vehicleType;
    private VehicleStatusEnum status;
    private Integer operatorId;
    private String statusNote;
    private String createdBy;
    private String updatedBy;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private ZonedDateTime deletedTime;
    private Boolean deleted = false;

}
