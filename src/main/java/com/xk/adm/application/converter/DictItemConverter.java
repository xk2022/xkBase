package com.xk.adm.application.converter;

import com.xk.adm.application.dto.DictItemRequest;
import com.xk.adm.application.dto.DictItemResponse;
import com.xk.adm.domain.model.bo.DictItemBO;
import com.xk.adm.domain.model.entity.DictItemEntity;
import com.xk.adm.domain.model.po.DictItemPO;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DictItemConverter {


    public DictItemEntity toEntity(DictItemRequest request) {
        return XkBeanUtils.copyProperties(request,DictItemEntity::new);
    }

    public DictItemPO BOtoPO(DictItemBO dictItemBO) {
        return XkBeanUtils.copyProperties(dictItemBO ,DictItemPO::new );
    }

    public DictItemBO toBo(DictItemPO saved) {
        return XkBeanUtils.copyProperties(saved ,DictItemBO::new );
    }

    public DictItemPO toPo(DictItemEntity entity) {
        return XkBeanUtils.copyProperties(entity ,DictItemPO::new );
    }

    public DictItemResponse toResponse(DictItemBO result) {
        return XkBeanUtils.copyProperties(result ,DictItemResponse::new );
    }
}
