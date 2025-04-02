package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpmsPermissionRepository extends JpaRepository<UpmsPermission, Long> {


}
