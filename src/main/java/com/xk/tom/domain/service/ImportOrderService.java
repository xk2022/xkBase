package com.xk.tom.domain.service;

import com.xk.tom.application.model.ImportOrderCmd;
import com.xk.tom.domain.model.bo.ImportOrderBo;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.infrastructure.persistence.model.po.ImportOrderPo;

import java.util.List;
import java.util.UUID;

/**
 * 📌 `ImportOrderService`
 * - 提供 **進口訂單（Order）** 的核心業務邏輯
 *
 * @author yuan Created on 2025/08/04.
 */
public interface ImportOrderService {

    ImportOrderBo create(ImportOrderCmd createData);

    ImportOrderPo findByUuid(UUID uuid);

    List<ImportOrderPo> findByContainerNumber(String containerNumber);

    void delete(UUID uuid);

    ImportOrderEntity getOrder(UUID uuid);

}
