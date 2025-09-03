package com.xk.adm.application.usecase.impl;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.application.mapper.DictCategoryMapper;
import com.xk.adm.application.usecase.DictCategoryCreateUseCase;
import com.xk.adm.domain.model.bo.AdmSystemCreateBO;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.service.DictCategoryService;
import com.xk.common.util.XkBeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


/**
 * 📌 `DictCategoryCreateUseCaseImpl` - 负责選單類別的创建逻辑
 *
 * - 处理 `DictCategoryRequest` 并转换为 `DictCategoryPo`
 * - 通过 `DictCategoryService` 进行业务验证和存储
 * - 返回 `DictCategoryResponse`
 *
 * @author hank Created on 2025/09/03.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DictCategoryCreateUseCaseImpl implements DictCategoryCreateUseCase {

    private final DictCategoryService service;
    private final DictCategoryMapper mapper;

    @Transactional
    @Override
    public DictCategoryResponse create(DictCategoryRequest request) {
        log.info(" [UseCase] 開始創建選單類別 - Code: {}", request.getCode());


        var entity = mapper.toEntity(request);
        entity.initialize();

        DictCategoryBO result =  request.getUuid() == null
                ? service.create(entity)
                : service.update(UUID.fromString(request.getUuid()) , entity);
        DictCategoryResponse response = mapper.toResponse(result);

        return response;
    }
}
