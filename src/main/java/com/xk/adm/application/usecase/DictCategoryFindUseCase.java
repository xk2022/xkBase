package com.xk.adm.application.usecase;


import com.xk.adm.application.dto.DictCategoryResponse;

public interface DictCategoryFindUseCase {
    DictCategoryResponse getDictCategory(String categoryCode);
}
