package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UpmsUserRoleRepository extends JpaRepository<UpmsUserRole, UUID>, JpaSpecificationExecutor<UpmsUserRole> {

    Optional<UpmsUserRole> findByUserUuid(UUID userUuid);

}
