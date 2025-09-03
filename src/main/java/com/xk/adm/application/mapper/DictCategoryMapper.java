package com.xk.adm.application.mapper;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DictCategoryMapper {


    public DictCategoryBO toBo(DictCategoryRequest request) {
        return XkBeanUtils.copyProperties(request , DictCategoryBO::new );
    }

    public DictCategoryEntity toEntity(DictCategoryRequest request) {
        return XkBeanUtils.copyProperties(request ,DictCategoryEntity::new );
    }

    public DictCategoryResponse toResponse(DictCategoryBO result) {
        return XkBeanUtils.copyProperties(result ,DictCategoryResponse::new );
    }
}
