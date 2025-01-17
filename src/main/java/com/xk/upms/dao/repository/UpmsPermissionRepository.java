package com.xk.upms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.xk.upms.model.po.UpmsPermission;

@Repository
public interface UpmsPermissionRepository extends JpaRepository<UpmsPermission,Long> ,JpaSpecificationExecutor<UpmsPermission> {

}
