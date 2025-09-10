package com.xk.adm.domain.dao.mapper;

import com.xk.adm.domain.model.entity.DictItemEntity;
import com.xk.adm.domain.model.po.DictItemPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictItemMapper {

    List<DictItemPO> findByCategoryCodeAndDelete0(String categoryCode);

    void deleteItems(List<DictItemPO> items);
}
