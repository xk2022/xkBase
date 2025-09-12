package com.xk.adm.domain.dao.repository;

import com.xk.adm.domain.model.po.DictCategoryPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DictCategoryRepository  extends JpaRepository<DictCategoryPO , UUID> {

    @Query(value = """
            Select * From dict_category Where code = :code and deleted ='0'
            """,nativeQuery = true)
    DictCategoryPO findByCodeAndDeleted(String code);
}
