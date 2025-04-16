package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UpmsRolePermissionActionRepository extends JpaRepository<UpmsRolePermissionAction, Long> {

    List<UpmsRolePermissionAction> findByIsDeletedFalseAndRoleUuidAndPermissionUuidIn(UUID roleUuid, List<UUID> upmsPermissionUuids);

}
