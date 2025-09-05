package com.xk.car.application.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 車頭與板車管理
 * hank create 8/31
 */
@Data
public class VehiclePairingsRequest {

    @Schema(description = "uuid")
    private String uuid;

    @Schema(description = "車牌號碼")
    private String licensePlate;

    @Schema(description = "綁定時間")
    private String bindTime;

    @Schema(description = "解除時間（仍綁定中則為 NULL）")
    private String unbindTime;

    @Schema(description = "綁定操作人 ID")
    private String bindBy;

    @Schema(description = "解除操作人 ID（可為 NULL）")
    private String unbindBy;

    @Schema(description = "備註")
    private String note;

    @Schema(description = "創建人員")
    private String createdBy;

    @Schema(description = "更新人員")
    private String updatedBy;
}
