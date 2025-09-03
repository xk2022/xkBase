package com.xk.upms.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xk.upms.domain.model.po.UpmsAction;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface UpmsActionRepository extends JpaRepository<UpmsAction, UUID> {

    List<UpmsAction> findByDeletedFalseOrderByOrdersAsc();

    List<UpmsAction> findByDeletedFalseAndUuidInOrderByOrdersAsc(List<UUID> upmsActionUuids);

}
