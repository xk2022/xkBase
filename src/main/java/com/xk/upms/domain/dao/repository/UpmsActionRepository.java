package com.xk.upms.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xk.upms.domain.model.po.UpmsAction;

import java.util.List;

@Repository
public interface UpmsActionRepository extends JpaRepository<UpmsAction, Long> {

    List<UpmsAction> findByPermissionId(Long permissionId);

}
