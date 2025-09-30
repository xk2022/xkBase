package com.xk.car.domain.model.bo;

import com.xk.car.domain.model.enums.VehicleEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;


/**
 *  耗損與維修項目紀錄
 * @author hank created 2025/08/13.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class VehiclePartsUsageBo {

    private UUID uuid;
    private String carId;
    private String partName;
    private VehicleEnum vehicleType;
    private BigDecimal mileage;
    private BigDecimal cost;
    private String description;
    private Date usedAt;
    private Boolean deleted;
    private ZonedDateTime deletedTime;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;





}
