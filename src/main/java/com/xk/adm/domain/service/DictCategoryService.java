package com.xk.adm.domain.service;

import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;
import java.util.UUID;

/**
 * 📌 `DictCategoryService`
 *
 * - 負責 `DictCategory`（選單項目系統）的創建、更新、刪除、查詢業務邏輯
 * - 透過 `DictCategoryRepository` 存取數據
 * - 內建軟刪除機制
 *
 * @author hank Created on 2025/09/03.
 */
public interface DictCategoryService {
    DictCategoryBO create(DictCategoryEntity entity);

    DictCategoryBO update(DictCategoryBO dictCategoryBO, DictCategoryEntity entity);

    void delete(UUID uuid);

    DictCategoryBO findByCode(String code);

    DictCategoryBO getDictCategory(String categoryCode);
}
