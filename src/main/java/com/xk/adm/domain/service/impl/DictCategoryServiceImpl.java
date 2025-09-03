package com.xk.adm.domain.service.impl;

import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;
import com.xk.adm.domain.service.DictCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `DictCategoryServiceImpl`
 *
 * - `DictCategoryService` 介面的實作
 * - 負責 `DictCategory`（管理系統）的創建、更新、刪除、查詢業務邏輯
 * - 透過 `DictCategoryRepository` 存取數據
 * - 內建軟刪除機制
 *
 * @author hank Created on 2025/09/03.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DictCategoryServiceImpl implements DictCategoryService {

    @Override
    public DictCategoryBO create(DictCategoryEntity entity) {
        return null;
    }

    @Override
    public DictCategoryBO update(UUID uuid, DictCategoryEntity entity) {
        return null;
    }
}
