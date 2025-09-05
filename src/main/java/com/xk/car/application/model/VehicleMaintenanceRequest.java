package com.xk.car.application.model;

import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * 車輛性能監控與維修提醒 request
 * Hank create 8/20
 */
@Data
public class VehicleMaintenanceRequest {


    @Schema(description = "uuid")
    private String uuid;

    @Schema(description = "車牌號碼")
    private String licensePlate;

    @Schema(description = "維修類型")
    private String maintenanceType;

    @Schema(description = "維修時的里程")
    private String mileageAt;

    @Schema(description = "維修日期")
    private String maintenanceDate;

    @Schema(description = "維修項目或情況說明")
    private String description;

    @Schema(description = "維修金額")
    private String cost;

    @Schema(description = "提醒方式")
    private String reminderType;

    @Schema(description = "下次預定保養里程")
    private String nextDueMileage;

    @Schema(description = "下次預定保養日期")
    private String nextDueDate;

    @Schema(description = "創建人員")
    private String createdBy;

    @Schema(description = "更新人員")
    private String updatedBy;

}
