package com.xk.adm.domain.service;


import com.xk.adm.domain.model.bo.DictItemBO;
import com.xk.adm.domain.model.entity.DictItemEntity;
import jakarta.validation.constraints.NotBlank;

public interface DictItemService {
    DictItemBO findByItemCodeAndDeleted0(@NotBlank String itemCode);

    DictItemBO create(DictItemEntity entity);

    DictItemBO update(DictItemBO dictItemBO, DictItemEntity entity);
}
