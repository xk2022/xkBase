package com.xk.adm.application.usecase.impl;

import com.xk.adm.application.usecase.DictCategoryDeleteUseCase;
import com.xk.adm.domain.service.DictCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `DictCategoryDeleteUseCaseImpl` - 负责選單類別的刪除逻辑
 *
 * - 处理 `DictCategoryRequest` 并转换为 `DictCategoryPo`
 * - 通过 `DictCategoryService` 进行业务验证和存储
 * - 返回 `DictCategoryResponse`
 *
 * @author hank Created on 2025/09/09.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictCategoryDeleteUseCaseImpl implements DictCategoryDeleteUseCase {

    private final DictCategoryService service;

    @Override
    public void delete(UUID uuid) {
        log.info("📌 [UseCase] 刪除選單類別: {}", uuid);

        // 直接呼叫 Service，內部自行判斷是否存在
        service.delete(uuid);
    }
}
