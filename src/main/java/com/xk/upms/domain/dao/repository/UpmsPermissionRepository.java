package com.xk.upms.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.xk.upms.domain.model.po.UpmsPermission;

import java.util.List;


@Repository
public interface UpmsPermissionRepository extends JpaRepository<UpmsPermission,Long> ,JpaSpecificationExecutor<UpmsPermission> {


    List<UpmsPermission> findById(List<Long> id);
}
