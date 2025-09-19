package com.xk.adm.application.usecase.impl;

import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.application.dto.DictItemResponse;
import com.xk.adm.application.usecase.DictCategoryFindUseCase;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.service.DictCategoryService;
import com.xk.adm.domain.service.DictItemService;

import com.xk.common.util.XkBeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 📌 `DictCategoryFindUseCaseImpl` - 负责選單類別的查詢逻辑
 *
 * - 处理 `DictCategoryRequest` 并转换为 `DictCategoryPo`
 * - 通过 `DictCategoryService` 进行业务验证和存储
 * - 返回 `DictCategoryResponse`
 *
 * @author hank Created on 2025/09/17
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DictCategoryFindUseCaseImpl implements DictCategoryFindUseCase {


    private final DictCategoryService service;
    private final DictItemService itemService;


    @Override
    public DictCategoryResponse getDictCategory(String categoryCode) {
        DictCategoryBO dictCategoryBO = service.getDictCategory(categoryCode);
        DictCategoryResponse response =new DictCategoryResponse();
        List<DictItemResponse> itemResponseList = XkBeanUtils.copyListProperties(dictCategoryBO.getItemList(),DictItemResponse::new);
        response.setCode(dictCategoryBO.getCode());
        response.setName(dictCategoryBO.getName());
        response.setDescription(dictCategoryBO.getDescription());
        response.setItemList(itemResponseList);

        return response;
    }
}
