package com.xk.tom.application.model;

import com.xk.tom.domain.model.vo.ContainerNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 📌 ImportOrderUpdateCmd
 * - 更新進口訂單的指令物件 (Command)
 * - 專門用於 UseCase → Service 傳輸更新資料
 * - 不允許更新 UUID、orderId、createdAt
 * <p>
 * 👉 用於：ImportOrderManageUseCaseImpl → ImportOrderService.update()
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderUpdateCmd {

    @Schema(description = "客戶 ID", example = "CUST001")
    private String customerId;

    @Schema(description = "進口日期", example = "2025-08-06")
    private LocalDate shipDate;

    @Schema(description = "船公司", example = "長榮海運")
    private String shippingCompany;

    @Schema(description = "船名/航次", example = "EVER GIVEN / 123A")
    private String vesselVoyage;

    @Schema(description = "櫃號", example = "EITU1234567")
    private ContainerNumber containerNumber;

    @Schema(description = "櫃型", example = "40HQ")
    private String containerType;

    @Schema(description = "櫃場", example = "基隆碼頭")
    private String containerYard;

    @Schema(description = "領櫃期限", example = "2025-08-15")
    private LocalDate lastPickupDate;

    @Schema(description = "提貨單號 (DO)", example = "DO-20250806-01")
    private String doNumber;

    @Schema(description = "提貨單位置", example = "台北倉儲 A 區")
    private String doLocation;

    @Schema(description = "送貨地點", example = "桃園物流園區")
    private String deliveryLocation;

    @Schema(description = "送貨日期", example = "2025-08-10")
    private LocalDate deliveryDate;

    @Schema(description = "送貨時間", example = "14:30")
    private LocalTime deliveryTime;

    @Schema(description = "還櫃地點", example = "基隆碼頭")
    private String returnYard;

    @Schema(description = "還櫃日期", example = "2025-08-12")
    private LocalDate returnDate;

    @Schema(description = "還櫃時間", example = "16:00")
    private LocalTime returnTime;

    @Schema(description = "備註", example = "需冷藏櫃，溫度保持 5°C")
    private String remark;

}
