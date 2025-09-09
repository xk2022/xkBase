package com.xk.adm.domain.dao.repository;

import com.xk.adm.domain.model.po.DictCategoryPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DictCategoryRepository  extends JpaRepository<DictCategoryPO , UUID> {

    DictCategoryPO findByCode(String code);
}
