package com.xk.upms.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xk.upms.domain.model.po.UpmsRolePermission;

@Repository
public interface UpmsRolePermissionRepository extends JpaRepository<UpmsRolePermission,Long> {

}
