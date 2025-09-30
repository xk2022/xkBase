package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.VehicleEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * 里程紀錄
 * @author hank create 2025/09/01
 */
@Data
public class VehicleTripLogsBo {

    private UUID uuid;
    private String carId;
    private String orderId;
    private VehicleEnum vehicleType;
    private BigDecimal startMileage;
    private BigDecimal endMileage;
    private BigDecimal distance;
    private Date date;
    private Boolean deleted;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;
}
