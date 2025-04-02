package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpmsUserRoleRepository extends JpaRepository<UpmsUserRole, Long>, JpaSpecificationExecutor<UpmsUserRole> {

    Optional<UpmsUserRole> findByUpmsUser_Id(Long id);

}
