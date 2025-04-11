package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRoleSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpmsRoleSystemRepository extends JpaRepository<UpmsRoleSystem, Long> {

    List<UpmsRoleSystem> findByIsDeletedFalse();

    List<UpmsRoleSystem> findByIsDeletedFalseAndRoleIdOrderByIdAsc(Long roleId);

}
