package com.xk.tom.infra.persistence.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.bo.ExportOrderBo;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.infra.persistence.model.po.ExportOrderPo;
import org.springframework.stereotype.Component;

/**
 * 📌 ExportPersistenceMapper
 * - 負責 ExportOrder Po ↔ Entity ↔ Bo 的轉換
 * <p>
 * Infra Layer：Persistence 層轉換器
 * ✅ Po（資料庫持久化對象） ↔ Entity（領域模型）
 * ✅ Entity ↔ Bo（Service/UseCase 傳輸物件）
 *
 * @author yuan Created on 2025/08/06.
 */
@Component
public class ExportPersistenceMapper {

    /**
     * ✅ Po → Entity
     */
    public ExportOrderEntity toEntity(ExportOrderPo input) {
        return XkBeanUtils.copyProperties(input, ExportOrderEntity::new);
    }

    /**
     * ✅ Entity → Po
     */
    public ExportOrderPo toPo(ExportOrderEntity input) {
        return XkBeanUtils.copyProperties(input, ExportOrderPo::new);
    }

    /**
     * ✅ Entity → Bo
     */
    public ExportOrderBo toBo(ExportOrderEntity input) {
        return XkBeanUtils.copyProperties(input, ExportOrderBo::new);
    }

    /**
     * ✅ Bo → Entity
     */
    public ExportOrderEntity toEntity(ExportOrderBo input) {
        return XkBeanUtils.copyProperties(input, ExportOrderEntity::new);
    }

    /**
     * ✅ Po → Bo
     */
    public ExportOrderBo toBo(ExportOrderPo input) {
        return XkBeanUtils.copyProperties(input, ExportOrderBo::new);
    }

    /**
     * ✅ Bo → Po
     */
    public ExportOrderPo toPo(ExportOrderBo input) {
        return XkBeanUtils.copyProperties(input, ExportOrderPo::new);
    }

}
