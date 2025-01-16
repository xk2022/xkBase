package com.xk.upms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.xk.upms.model.po.UpmsRole;

@Repository
public interface UpmsRoleRepository extends JpaRepository<UpmsRole, Long>   , JpaSpecificationExecutor<UpmsRole> {

}
