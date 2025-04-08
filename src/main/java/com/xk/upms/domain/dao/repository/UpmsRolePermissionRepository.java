package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UpmsRolePermissionRepository  extends JpaRepository<UpmsRolePermission, Long> {

    List<UpmsRolePermission> findByIsDeletedFalseAndSystemUuidAndRoleId(UUID systemUuid, Long roleId);

}
