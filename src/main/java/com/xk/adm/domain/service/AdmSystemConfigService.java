package com.xk.adm.domain.service;

import java.util.Optional;
import java.util.UUID;

import com.xk.adm.domain.model.systemConfig.SystemConfigBO;

/**
 * 📌 `SystemConfigService` - 系統設定領域服務
 * 
 * - **處理業務邏輯**
 * - **對應 `BO` 物件，而非直接使用 `PO`**
 * - **內部負責轉換 `PO -> BO`**
 * 
 * @author yuan Created on 2025/02/24.
 */
public interface AdmSystemConfigService {

    /**
     * 獲取最新的系統設定
     *
     * @return Optional<SystemConfigBO>
     */
    Optional<SystemConfigBO> findLatest();

    /**
     * 根據 UUID 查找系統設定
     *
     * @param id 設定的 UUID
     * @return Optional<SystemConfigBO>
     */
    Optional<SystemConfigBO> findById(UUID id);

    /**
     * 儲存或更新系統設定
     *
     * @param systemConfigBO 系統設定業務物件
     * @return SystemConfigBO
     */
    SystemConfigBO save(SystemConfigBO request);

}