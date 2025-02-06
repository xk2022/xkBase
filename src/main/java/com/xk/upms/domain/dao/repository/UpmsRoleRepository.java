package com.xk.upms.domain.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xk.upms.domain.model.po.UpmsRole;

@Repository
public interface UpmsRoleRepository extends JpaRepository<UpmsRole, Long> {
	
	
	
	Optional<UpmsRole> findByCode(String code);
	
    List<UpmsRole> findByCodeStartingWithOrderByOrdersAsc(String prefix);

}
