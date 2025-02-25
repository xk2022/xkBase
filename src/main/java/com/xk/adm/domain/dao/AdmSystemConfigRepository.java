package com.xk.adm.domain.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xk.adm.domain.model.systemConfig.SystemConfigPO;

/**
 * 📌 `AdmSystemConfigRepository` - 提供 SystemConfigPO 的 JPA 操作
 * 
 * @author yuan Created on 2025/02/24.
 */
@Repository
public interface AdmSystemConfigRepository extends JpaRepository<SystemConfigPO, UUID> {

//    Optional<SystemConfig> findLatest(); // 取得最新的設定
	Optional<SystemConfigPO> findTopByOrderByCreatedTimeDesc(); // 取得最新的設定

	Optional<SystemConfigPO> findTopByOrderByIdDesc();

}