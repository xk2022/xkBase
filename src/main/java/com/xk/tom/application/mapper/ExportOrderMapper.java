package com.xk.tom.application.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.*;
import com.xk.tom.domain.model.bo.ExportOrderBo;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import org.springframework.stereotype.Component;

/**
 * 📌 ExportOrderMapper
 * - 負責 ExportOrder 的 Dto ↔ Entity / Bo / Cmd 轉換
 *
 * @author yuan Created on 2025/08/06.
 */
@Component
public class ExportOrderMapper {

    public ExportOrderEntity toEntity(ExportOrderCreateCmd input) {
        return XkBeanUtils.copyProperties(input, ExportOrderEntity::new);
    }

    public ExportOrderCreateCmd toCreateCmd(ExportOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, ExportOrderCreateCmd::new);
    }

    public ExportOrderUpdateCmd toUpdateCmd(ExportOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, ExportOrderUpdateCmd::new);
    }

    public OrderResponseDto toResponseDto(ExportOrderBo input) {
        return XkBeanUtils.copyProperties(input, OrderResponseDto::new);
    }

    public OrderAssignCmd toAssignCmd(AssignOrderRequestDto input) {
        return XkBeanUtils.copyProperties(input, OrderAssignCmd::new);
    }

    public OrderUpdateStatusCmd toUpdateStatusCmd(UpdateOrderStatusRequestDto input) {
        return XkBeanUtils.copyProperties(input, OrderUpdateStatusCmd::new);
    }

    public ExportOrderBo toBo(ExportOrderEntity input) {
        return XkBeanUtils.copyProperties(input, ExportOrderBo::new);
    }

    public ExportOrderEntity toCreateEntity(ExportOrderCreateCmd createData) {
        return XkBeanUtils.copyProperties(createData, ExportOrderEntity::new);
    }

}
