package com.xk.tom.application.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.*;
import com.xk.tom.domain.model.bo.ImportOrderBo;
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

    public ImportOrderEntity toEntity(ImportOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, ImportOrderEntity::new);
    }

    public ImportOrderCreateCmd toCreateCmd(ImportOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, ImportOrderCreateCmd::new);
    }

    public ImportOrderUpdateCmd toUpdateCmd(ImportOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, ImportOrderUpdateCmd::new);
    }

    public OrderResponseDto toResponseDto(ImportOrderBo input) {
        return XkBeanUtils.copyProperties(input, OrderResponseDto::new);
    }

    public OrderAssignCmd toAssignCmd(AssignOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, OrderAssignCmd::new);
    }

    public OrderUpdateStatusCmd toUpdateStatusCmd(UpdateOrderStatusRequestDto input) {
        return XkBeanUtils.copyProperties(input, OrderUpdateStatusCmd::new);
    }

    public ImportOrderBo toBo(ImportOrderEntity input) {
        return XkBeanUtils.copyProperties(input, ImportOrderBo::new);
    }

    public ImportOrderEntity toCreateEntity(ImportOrderCreateCmd createData) {
        return XkBeanUtils.copyProperties(createData, ImportOrderEntity::new);
    }
}
