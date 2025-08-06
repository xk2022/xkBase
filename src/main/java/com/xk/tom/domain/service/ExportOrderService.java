package com.xk.tom.domain.service;

import com.xk.tom.domain.model.entity.ExportOrderEntity;

import java.util.UUID;

/**
 *
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ExportOrderService {

    ExportOrderEntity getOrder(UUID uuid);

}
