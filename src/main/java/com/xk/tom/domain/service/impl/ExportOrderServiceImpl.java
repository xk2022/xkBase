package com.xk.tom.domain.service.impl;

import com.xk.tom.domain.dao.repository.ExportOrderRepository;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.service.ExportOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExportOrderServiceImpl implements ExportOrderService {

    private final ExportOrderRepository exportOrderRepository;

    public ExportOrderEntity getOrder(UUID uuid) {
        return exportOrderRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Export order not found"));
    }
}


