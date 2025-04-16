package com.xk.adm.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.bo.AdmSystemCreateBO;
import com.xk.adm.domain.model.bo.AdmSystemInitBO;
import com.xk.adm.domain.model.bo.AdmSystemSearchBO;
import com.xk.adm.domain.model.bo.AdmSystemUpdateBO;

/**
 * 📌 `AdmSystemService`
 * 
 * - 提供 `ADM System`（管理系統）的業務邏輯
 * - 包含 **查詢、建立、更新、軟刪除、恢復、徹底刪除** 功能
 * - **支援條件查詢與分頁**
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemService {

	// ============= 🟢【C】Create（建立）================
    /**
     * 建立新系統
     *
     * @param systemBO 系統 `BO` 物件
     * @return 儲存後的 `AdmSystemBO`
     */
    AdmSystemBO create(AdmSystemCreateBO createData);

	/**
	 * 📌 批次建立或更新系統
	 * 
	 * @param boList 需要儲存或更新的系統物件列表
	 * @return 已儲存的系統物件列表
	 */
	List<AdmSystemBO> saveAll(List<AdmSystemInitBO> boList);

	// ============= 🔵【R】Read（查詢）================
    /**
     * 根據 `uuid` 取得系統資訊
     *
     * @param uuid 系統唯一識別碼
     * @return `AdmSystemBO`，如果找不到則回傳 `Optional.empty()`
     */
    Optional<AdmSystemBO> findByUuid(UUID uuid);
    
    /**
     * 根據 `code` 取得系統資訊
     *
     * @param code 系統代碼
     * @return `AdmSystemBO`，如果找不到則回傳 `Optional.empty()`
     */
    Optional<AdmSystemBO> findByCode(String code);

    /**
     * 檢查系統是否存在（根據 UUID）
     *
     * @param uuid 系統唯一識別碼
     * @return 如果系統存在回傳 `true`，否則回傳 `false`
     */
    boolean existsById(UUID uuid);

    /**
	 * 檢查系統代號是否存在（避免重複）
	 * 
	 * @param code 系統唯一代號
     * @return 如果系統存在回傳 `true`，否則回傳 `false`
	 */
    boolean existsByCode(String code);

	// ============= ⚡【條件查詢 & 過濾】================
    /**
     * 取得所有系統列表（僅限未刪除的系統）
     *
     * @return 所有系統的 `AdmSystemBO` 列表
     */
    List<AdmSystemBO> findAll();

    /**
     * 根據條件查詢系統列表（支援分頁）
     *
     * @param searchParams 查詢條件
     * @param pageable 分頁參數
     * @return 符合條件的 `AdmSystemBO` 分頁結果
     */
    List<AdmSystemBO> search(AdmSystemSearchBO searchParams);

	// ============= 🟡【U】Update（更新）================
    /**
     * 更新系統資訊（完整更新）
     *
     * @param uuid 系統唯一識別碼
     * @param systemBO 需要更新的 `BO` 物件
     * @return 更新後的 `AdmSystemBO`
     */
    AdmSystemBO update(UUID uuid, AdmSystemUpdateBO updateData);

	// ============= 🔴【D】Delete（刪除）================
    /**
     * 軟刪除系統（僅做邏輯刪除）
     *
     * @param uuid 系統唯一識別碼
     */
    void softDelete(UUID uuid);

    /**
     * 恢復已刪除的系統
     *
     * @param uuid 系統唯一識別碼
     */
    void restore(UUID uuid);

    /**
     * 徹底刪除系統（物理刪除）
     *
     * @param uuid 系統唯一識別碼
     */
    void delete(UUID uuid);
}
