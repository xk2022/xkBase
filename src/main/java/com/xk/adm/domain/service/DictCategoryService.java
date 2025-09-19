package com.xk.adm.domain.service;

import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;

import java.util.UUID;

public interface DictCategoryService {
    DictCategoryBO create(DictCategoryEntity entity);

    DictCategoryBO update(DictCategoryBO dictCategoryBO, DictCategoryEntity entity);

    void delete(UUID uuid);

    DictCategoryBO findByCode(String code);

    DictCategoryBO getDictCategory(String categoryCode);
}
