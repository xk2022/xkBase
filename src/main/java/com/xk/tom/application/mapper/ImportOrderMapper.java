package com.xk.tom.application.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.ImportOrderRequestDto;
import com.xk.tom.application.model.ImportOrderResponseDto;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import org.springframework.stereotype.Component;

/**
 * 📌 ImportOrderMapper
 * - 負責 ImportOrder 的 Dto ↔ Entity 轉換
 *
 * @author yuan Created on 2025/08/05.
 */
@Component
public class ImportOrderMapper {

    public ImportOrderEntity toEntity(ImportOrderRequestDto dto) {
        return XkBeanUtils.copyProperties(dto, ImportOrderEntity::new);
    }

    public ImportOrderResponseDto toResponse(ImportOrderEntity entity) {
        return XkBeanUtils.copyProperties(entity, ImportOrderResponseDto::new);
    }
}
