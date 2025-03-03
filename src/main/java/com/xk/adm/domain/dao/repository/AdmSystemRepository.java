package com.xk.adm.domain.dao.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xk.adm.domain.model.po.AdmSystemPO;

/**
 * 📌 `AdmSystemRepository`
 *
 * - 負責與 `adm_system` 資料表進行資料存取（DAO 層）
 * - 使用 Spring Data JPA 提供標準的 CRUD 操作
 * - 支援 **自訂查詢方法**
 * 
 * @author yuan Created on 2025/02/25.
 */
@Repository
public interface AdmSystemRepository extends JpaRepository<AdmSystemPO, UUID> {

    /**
     * 根據 `code` 查找系統（返回 PO）
     *
     * @param code 系統代碼
     * @return `AdmSystem`，如果找不到則返回 `Optional.empty()`
     */
    Optional<AdmSystemPO> findByCode(String code);

    /**
     * 查找所有 **未刪除** 的系統（返回 PO）
     *
     * @return 所有 `deleted = false` 的 `AdmSystem`
     */
    List<AdmSystemPO> findByDeletedFalse();

    /**
     * 查找啟用中的系統（返回 PO）
     *
     * @return `enabled = true` 的 `AdmSystem`
     */
    List<AdmSystemPO> findByEnabledTrueAndDeletedFalse();

    /**
     * 📌 檢查是否有相同系統代碼
     *
     * @param code 系統代碼
     * @return 如果存在則回傳 `true`
     */
    boolean existsByCode(String code);
    
}
