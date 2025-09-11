package com.xk.adm.domain.dao.repository;

import com.xk.adm.domain.model.po.DictItemPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DictItemRepository extends JpaRepository<DictItemPO , UUID> {

    @Query("""
            Select * From dict_item Where category_code = :code And deleted ='0'
            """)
    List<DictItemPO> findCateGoryCodeAndDeleted(String code);
}
