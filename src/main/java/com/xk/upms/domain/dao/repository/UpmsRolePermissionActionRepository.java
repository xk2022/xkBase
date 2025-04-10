package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpmsRolePermissionActionRepository extends JpaRepository<UpmsRolePermissionAction, Long> {

    List<UpmsRolePermissionAction> findByIsDeletedFalseAndRoleIdAndPermissionIdIn(Long roleId, List<Long> upmsPermissionIds);

}
