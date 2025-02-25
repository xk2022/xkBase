package com.xk.adm.domain.service;

import java.util.List;
import java.util.Optional;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.bo.AdmSystemInitBO;

/**
 * 📌 `AdmSystemService`
 * 
 * - 提供 `ADM System`（管理系統）的業務邏輯
 * - 包含 **查詢、創建、更新、軟刪除、恢復** 功能
 * - **支援條件查詢**
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemService {
	
	/**
	 * 📌 批次創建或更新系統
	 * 
	 * @param boList 需要儲存或更新的使用者物件列表
	 * @return 已儲存的使用者物件列表
	 */
	List<AdmSystemBO> saveAllSystems(List<AdmSystemInitBO> boList);

    /**
     * 獲取所有系統列表（僅限未刪除的系統）
     *
     * @return 所有系統的 `AdmSystemBO` 列表
     */
    List<AdmSystemBO> getAllSystems();

    /**
     * 根據條件查詢系統列表
     *
     * @param searchParams 查詢條件（可選）
     * @return 符合條件的 `AdmSystemBO` 列表
     */
    List<AdmSystemBO> searchSystems(AdmSystemBO systemBO);

    /**
     * 根據 `uuid` 獲取系統資訊
     *
     * @param uuid 系統唯一識別碼
     * @return `AdmSystemBO`，如果找不到則返回 `Optional.empty()`
     */
    Optional<AdmSystemBO> findById(String uuid);
    
    /**
     * 根據 `code` 獲取系統資訊
     *
     * @param code 系統代碼
     * @return `AdmSystemBO`，如果找不到則返回 `Optional.empty()`
     */
    Optional<AdmSystemBO> getSystemByCode(String code);

    /**
     * 創建新系統
     *
     * @param systemBO 系統 `BO` 物件
     * @return 儲存後的 `AdmSystemBO`
     */
    AdmSystemBO createSystem(AdmSystemBO systemBO);

    /**
     * 更新系統資訊
     *
     * @param systemBO 需要更新的 `BO` 物件
     * @return 更新後的 `AdmSystemBO`
     */
    AdmSystemBO update(AdmSystemBO systemBO);

    /**
     * 軟刪除系統
     *
     * @param uuid 系統唯一識別碼
     * @return 是否刪除成功
     */
    Boolean softDeleteSystem(String uuid);

    /**
     * 恢復已刪除的系統
     *
     * @param uuid 系統唯一識別碼
     * @return 是否恢復成功
     */
    Boolean restoreSystem(String uuid);

}
