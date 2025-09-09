package com.xk.adm.application.mapper;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;
import com.xk.adm.domain.model.po.DictCategoryPO;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DictCategoryMapper {


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
}
