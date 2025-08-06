package com.xk.tom.application.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 📌 ImportOrderCreateCmd
 * - Command 物件，用於「建立進口訂單」的動作
 * - 收集建立時所需的欄位，不包含系統自動生成的欄位（如 uuid、status、createdAt）
 * <p>
 * 👉 在 UseCase 中由 Mapper 轉換 RequestDto → Cmd
 * 👉 在 Service 中由 Cmd 轉換為 Entity，並執行業務邏輯
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderCreateCmd {

    /**
     * 客戶 ID (外鍵)
     */
    private String customerId;

    /**
     * 進口日期
     */
    private LocalDate shipDate;

    /**
     * 船公司
     */
    private String shippingCompany;

    /**
     * 船名 / 航次
     */
    private String vesselVoyage;

    /**
     * 櫃號
     */
    private String containerNumber;

    /**
     * 櫃型
     */
    private String containerType;

    /**
     * 櫃場
     */
    private String containerYard;

    /**
     * 領櫃期限
     */
    private LocalDate lastPickupDate;

    /**
     * 提貨單號 (DO)
     */
    private String doNumber;

    /**
     * 提貨單位置
     */
    private String doLocation;

    /**
     * 送貨地點
     */
    private String deliveryLocation;

    /**
     * 送貨日期
     */
    private LocalDate deliveryDate;

    /**
     * 送貨時間
     */
    private LocalTime deliveryTime;

    /**
     * 還櫃地點
     */
    private String returnYard;

    /**
     * 還櫃日期
     */
    private LocalDate returnDate;

    /**
     * 還櫃時間
     */
    private LocalTime returnTime;

    /**
     * 備註
     */
    private String remark;

}
