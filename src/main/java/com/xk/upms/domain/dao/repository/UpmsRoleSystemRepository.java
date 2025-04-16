package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRoleSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UpmsRoleSystemRepository extends JpaRepository<UpmsRoleSystem, Long> {

    List<UpmsRoleSystem> findByIsDeletedFalse();

    List<UpmsRoleSystem> findByIsDeletedFalseAndRoleUuidOrderByIdAsc(UUID roleUuid);

}
