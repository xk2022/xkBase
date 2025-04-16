package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UpmsPermissionRepository extends JpaRepository<UpmsPermission, Long> {

    List<UpmsPermission> findByIsDeletedFalseOrderByOrdersAsc();

    List<UpmsPermission> findByIsDeletedFalseAndUuidInOrderByOrdersAsc(List<UUID> upmsPermissionUuids);

}
