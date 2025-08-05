package com.xk.tom.application.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.ExportOrderRequestDto;
import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import org.springframework.stereotype.Component;

/**
 * 📌 ExportOrderMapper
 * - 負責 ExportOrder 的 Dto ↔ Entity 轉換
 *
 * @author yuan Created on 2025/08/05.
 */
@Component
public class ExportOrderMapper {

    public ExportOrderEntity toEntity(ExportOrderRequestDto dto) {
        return XkBeanUtils.copyProperties(dto, ExportOrderEntity::new);
    }

    public ExportOrderResponseDto toResponse(ExportOrderEntity entity) {
        return XkBeanUtils.copyProperties(entity, ExportOrderResponseDto::new);
    }

}
