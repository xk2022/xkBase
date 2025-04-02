package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsPermissionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpmsPermissionActionRepository extends JpaRepository<UpmsPermissionAction, Long> {

    List<UpmsPermissionAction> findByIsDeletedFalseAndPermissionIdIn(List<Long> permissionIds);

}
