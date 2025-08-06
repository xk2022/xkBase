package com.xk.tom.application.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class OrderAssignCmd {
    private UUID vehicleId;
    private UUID driverId;
    private UUID assignedBy;
    private LocalDate time;
}
