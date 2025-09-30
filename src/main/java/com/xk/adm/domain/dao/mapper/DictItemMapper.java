package com.xk.adm.domain.dao.mapper;

import com.xk.adm.domain.model.po.DictItemPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * 選單子項目
 * @author hank created on  2025/09/03.
 */
@Mapper
public interface DictItemMapper {

    /**
     * 刪除子項目
     * @param items
     */
    void deleteItems(List<DictItemPO> items);
}
