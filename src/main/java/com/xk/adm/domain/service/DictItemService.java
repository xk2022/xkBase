package com.xk.adm.domain.service;


import com.xk.adm.domain.model.bo.DictItemBO;
import com.xk.adm.domain.model.entity.DictItemEntity;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.UUID;


/**
 * 📌 `DictItemService`
 *
 * - 負責 `DictItem`（選單項目系統）的創建、更新、刪除、查詢業務邏輯
 * - 透過 `DictItemRepository` 存取數據
 * - 內建軟刪除機制
 *
 * @author hank Created on 2025/09/10.
 */
public interface DictItemService {
    DictItemBO findByItemCodeAndDeleted0(@NotBlank String itemCode);

    DictItemBO create(DictItemEntity entity);

    DictItemBO update(DictItemBO dictItemBO, DictItemEntity entity);

    void delete(UUID uuid) throws NotFoundException;
}
