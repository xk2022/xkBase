package com.xk.adm.domain.service;


import com.xk.adm.domain.model.bo.DictItemBO;
import com.xk.adm.domain.model.entity.DictItemEntity;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.UUID;

public interface DictItemService {
    DictItemBO findByItemCodeAndDeleted0(@NotBlank String itemCode);

    DictItemBO create(DictItemEntity entity);

    DictItemBO update(DictItemBO dictItemBO, DictItemEntity entity);

    void delete(UUID uuid) throws NotFoundException;
}
