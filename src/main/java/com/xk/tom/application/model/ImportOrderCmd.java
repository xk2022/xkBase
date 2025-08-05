package com.xk.tom.application.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * 📌 ImportOrderCmd
 * - 建立進口訂單時的命令物件
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderCmd {
    private String containerNumber;
    private String deliveryLocation;
    private LocalDate shipDate;
}