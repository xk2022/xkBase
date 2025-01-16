package com.xk.upms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UpmsPermission  extends JpaRepository<UpmsPermission, Long> , JpaSpecificationExecutor<UpmsPermission>{

}
