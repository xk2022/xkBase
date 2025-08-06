package com.xk.tom.domain.service;

import com.xk.tom.application.model.*;
import com.xk.tom.domain.model.bo.ImportOrderBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 📌 `ImportOrderService`
 * - 提供 **進口訂單（Order）** 的核心業務邏輯
 *
 * @author yuan Created on 2025/08/04.
 */
public interface ImportOrderService {

    ImportOrderBo findByUuid(UUID uuid);

    List<ImportOrderBo> findAll();

    Page<ImportOrderBo> findAll(Pageable pageable);

    List<ImportOrderBo> findByCondition(ImportOrderQueryDto condition);

    ImportOrderBo create(ImportOrderCreateCmd cmd);

    ImportOrderBo update(UUID uuid, ImportOrderUpdateCmd updateData);

    ImportOrderBo assign(UUID uuid, OrderAssignCmd cmd);

    ImportOrderBo updateStatus(UUID uuid, OrderUpdateStatusCmd cmd);

    ImportOrderBo restore(UUID uuid);

    void delete(UUID uuid);

}
