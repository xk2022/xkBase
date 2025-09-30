package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 車輛基本資訊
 * @author  hank create 8/31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class VehicleBo {

    private UUID uuid;
    private VehicleEnum vehicleType;
    private String licensePlate;
    private String brandModel;
    private String year;
    private BigDecimal mileage;
    private VehicleStatusEnum status;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;
    private Boolean deleted;
    private ZonedDateTime deletedTime;


}
