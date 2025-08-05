package com.xk.tom.application.model;

import lombok.Data;

/**
 * 📌 SaveImportOrderCmd
 * - 保存進口訂單命令物件 (可複用於 Create/Update)
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class SaveImportOrderCmd {
    private String containerNumber;
    private String deliveryLocation;
}