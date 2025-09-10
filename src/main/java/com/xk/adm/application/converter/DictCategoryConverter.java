package com.xk.adm.application.converter;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;
import com.xk.adm.domain.model.po.DictCategoryPO;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DictCategoryConverter {


    public DictCategoryBO toBo(DictCategoryPO po) {
        return XkBeanUtils.copyProperties(po , DictCategoryBO::new );
    }

    public DictCategoryEntity toEntity(DictCategoryRequest request) {
        return XkBeanUtils.copyProperties(request ,DictCategoryEntity::new );
    }

    public DictCategoryResponse toResponse(DictCategoryBO result) {
        return XkBeanUtils.copyProperties(result ,DictCategoryResponse::new );
    }

    public DictCategoryPO toPO(DictCategoryEntity entity) {
        return XkBeanUtils.copyProperties(entity , DictCategoryPO::new);
    }

    public DictCategoryPO BOtoPO(DictCategoryBO dictCategoryBO) {
        return XkBeanUtils.copyProperties(dictCategoryBO , DictCategoryPO::new);
    }
}
