package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 📌 ImportOrderQueryDto
 * - 用於查詢進口訂單的複合條件
 * - 會被 UseCase 傳入 Service 層
 * <p>
 * 👉 適用場景：
 * - 查詢列表 / 分頁查詢
 * - 匯出報表
 * - 條件篩選
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderQueryDto {

    /**
     * 客戶 ID
     */
    private String customerId;

    /**
     * 訂單狀態
     */
    private OrderStatus status;

    /**
     * 船公司 (模糊查詢可用 LIKE)
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
     * 起始進口日期
     */
    private LocalDate shipDateFrom;

    /**
     * 結束進口日期
     */
    private LocalDate shipDateTo;

    /**
     * 送貨地點 (模糊查詢可用 LIKE)
     */
    private String deliveryLocation;

    /**
     * 起始送貨日期
     */
    private LocalDate deliveryDateFrom;

    /**
     * 結束送貨日期
     */
    private LocalDate deliveryDateTo;

    @Schema(description = "開始日期 (出口日期)", example = "2025-08-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Schema(description = "結束日期 (出口日期)", example = "2025-08-31")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

}
