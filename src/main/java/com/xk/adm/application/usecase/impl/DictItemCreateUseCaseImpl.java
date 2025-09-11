package com.xk.adm.application.usecase.impl;

import com.xk.adm.application.converter.DictItemConverter;
import com.xk.adm.application.dto.DictItemRequest;
import com.xk.adm.application.dto.DictItemResponse;
import com.xk.adm.application.usecase.DictItemCreateUseCase;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.bo.DictItemBO;
import com.xk.adm.domain.service.DictCategoryService;
import com.xk.adm.domain.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * 📌 `DictItemCreateUseCaseImpl` - 负责選單類別的创建逻辑
 *
 * - 处理 `DictItemRequest` 并转换为 `DictItemPo`
 * - 通过 `DictItemService` 进行业务验证和存储
 * - 返回 `DictItemResponse`
 *
 * @author hank Created on 2025/09/10.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DictItemCreateUseCaseImpl implements DictItemCreateUseCase {

    private final DictItemService service;
    private final DictItemConverter converter;
    private final DictCategoryService dictCategoryService;

    @Override
    public DictItemResponse create(DictItemRequest request) throws NotFoundException {
        //查詢是否有該類別
        DictCategoryBO dictCategoryBO = dictCategoryService.findByCode(request.getCatrgoryCode());
        if (dictCategoryBO == null){
            throw new NotFoundException("請先建立選單類別");
        }

        DictItemBO dictItemBO = service.findByItemCodeAndDeleted0(request.getItemCode());
        log.info("[UseCase] 開始{}選單項目 - ItemCode={}",dictItemBO==null?"建立":"更新" ,request.getItemCode());

        var entity =converter.toEntity(request);
        entity.initialize();

        DictItemBO result = dictItemBO ==null
                ? service.create(entity)
                : service.update(dictItemBO , entity);

        return converter.toResponse(result);
    }
}
