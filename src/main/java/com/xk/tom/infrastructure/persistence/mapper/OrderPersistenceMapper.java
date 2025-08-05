package com.xk.tom.infrastructure.persistence.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.infrastructure.persistence.model.po.ExportOrderPo;
import com.xk.tom.infrastructure.persistence.model.po.ImportOrderPo;
import org.springframework.stereotype.Component;

/**
 * 📌 OrderPersistenceMapper
 *
 * - 負責 Entity ↔ Po 的轉換
 * - 使用 XkBeanUtils 進行屬性複製
 */
@Component
public class OrderPersistenceMapper {

    // ==== Import Order ====
    public ImportOrderPo toPo(ImportOrderEntity entity) {
        return XkBeanUtils.copyProperties(entity, ImportOrderPo::new);
    }

    public ImportOrderEntity toEntity(ImportOrderPo po) {
        return XkBeanUtils.copyProperties(po, ImportOrderEntity::new);
    }

    // ==== Export Order ====
    public ExportOrderPo toPo(ExportOrderEntity entity) {
        return XkBeanUtils.copyProperties(entity, ExportOrderPo::new);
    }

    public ExportOrderEntity toEntity(ExportOrderPo po) {
        return XkBeanUtils.copyProperties(po, ExportOrderEntity::new);
    }

}
