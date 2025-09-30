package com.xk.adm.domain.dao.mapper;

import com.xk.adm.domain.model.bo.DictCategoryBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * DictCategoryMapper
 * 查詢 選單類別 Mapper
 *
 * @author hank Created on 2025/09/19.
 */
@Mapper
public interface DictCategoryMapper {

    /**
     * 查詢選單類別 和該類別的子項目
     *
     * @param categoryCode
     * @return
     */
    DictCategoryBO getDictCategory(@Param("categoryCode") String categoryCode);
}
