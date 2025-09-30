package com.xk.adm.domain.dao.mapper;

import com.xk.adm.domain.model.bo.DictCategoryBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hank Created on 2025/09/19.
 */
@Mapper
public interface DictCategoryMapper {

    DictCategoryBO getDictCategory(@Param("categoryCode") String categoryCode);
}
