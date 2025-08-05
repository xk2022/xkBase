package com.xk.tom.domain.service;

import com.xk.tom.domain.model.entity.ExportOrderEntity;

import java.util.UUID;

public interface ExportOrderService {

    ExportOrderEntity getOrder(UUID uuid);

}
